package grpc.authentication;

import grpc.authentication.controllers.MasterComm;
import grpc.db.ClientDetails;
import grpc.db.Connector;
import grpc.db.NodeDetails;
import grpc.jwt.JwtHelper;
import grpc.server.AuthenticationServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.gateway.protos.*;
import org.master.protos.Status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Pattern;

public class AuthenticationService extends AuthenticateGrpc.AuthenticateImplBase {
    private enum TYPES {CLIENT, NODE};

    private JwtHelper jwtHelper;
    private Server svr;

    public AuthenticationService() {
        jwtHelper = new JwtHelper();
    }

    public static void main(String[] args) throws Exception {
        String path = args[0];
        try {
            Properties conf = AuthenticationService.getConfiguration(new File(path));
            AuthenticationServer.configure(conf);

            final AuthenticationService impl = new AuthenticationService();
            impl.start();
            impl.blockUntilShutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties getConfiguration(final File path) throws IOException {
        if (!path.exists())
            throw new IOException("missing file");

        Properties rtn = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            rtn.load(fis);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        return rtn;
    }

    private void start() throws Exception {
        svr = ServerBuilder.forPort(AuthenticationServer.getInstance().getServerPort()).addService(new AuthenticationService())
                .build();

        System.out.println("-- starting server");
        svr.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                AuthenticationService.this.stop();
            }
        });
    }

    protected void stop() {
        svr.shutdown();
    }

    private void blockUntilShutdown() throws Exception {
        svr.awaitTermination();
    }

    @Override
    public void register(Request request, StreamObserver<Reply> responseObserver) throws SQLException {
        String clientIp = request.getIp();
        String password = request.getPassword();
        String type = request.getType();

        Connector.QueryStatus status = Connector.QueryStatus.FAILURE;

        Reply.Builder registerReply = Reply.newBuilder();
        registerReply.setMessage("");
        registerReply.setMasterip("");
        registerReply.setToken("");

        if(!isIpValid(clientIp)) {
            registerReply.setMessage("Invalid ip address. Please enter IPV4 address");
            responseObserver.onNext(registerReply.build());
            responseObserver.onCompleted();

            return;
        }

        if (type.equalsIgnoreCase(TYPES.CLIENT.toString())) {
           status = ClientDetails.addNewClient(clientIp, password);
        }

        if (type.equalsIgnoreCase(TYPES.NODE.toString())) {
            status = NodeDetails.addNewNode(clientIp, password);
        }


        Status response = Status.SUCCESS;
        if (type.equalsIgnoreCase(TYPES.NODE.toString())) {
            response = MasterComm.notifyMaster(clientIp);
        }

        if(status == Connector.QueryStatus.FAILURE || response == Status.FAILURE) {
            registerReply.setMessage(type + " was not registered");
            Connector.getConnection().rollback();
            responseObserver.onNext(registerReply.build());
            responseObserver.onCompleted();

            return;
        }

        Connector.getConnection().commit();
        registerReply.setMessage(type + " registered successfully");

        responseObserver.onNext(registerReply.build());
        responseObserver.onCompleted();
    }

    @Override
    public void login(Request request, StreamObserver<Reply> responseObserver) throws SQLException {
        String clientIp = request.getIp();
        String password = request.getPassword();
        String type = request.getType();

        Reply.Builder loginReply = Reply.newBuilder();
        loginReply.setMessage("");
        loginReply.setToken("");
        loginReply.setMasterip("");

        boolean isValid = false;

        if(!isIpValid(clientIp)) {
            loginReply.setMessage("Invalid ip address. Please enter IPV4 address");
            responseObserver.onNext(loginReply.build());
            responseObserver.onCompleted();

            return;
        }

        if (type.equalsIgnoreCase(TYPES.CLIENT.toString())) {
            isValid = ClientDetails.validateClient(clientIp, password);
        }

        if (type.equalsIgnoreCase(TYPES.NODE.toString())) {
            isValid = NodeDetails.validateNode(clientIp, password);
        }

        if(!isValid) {
            loginReply.setMessage(type + " is not registered");

            Connector.getConnection().rollback();
            responseObserver.onNext(loginReply.build());
            responseObserver.onCompleted();

            return;
        }

        Connector.getConnection().commit();
        loginReply.setMessage(type + " logged in successfully");
        loginReply.setToken(jwtHelper.getToken(clientIp, type));
        loginReply.setMasterip("");

        responseObserver.onNext(loginReply.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getNodeForUpload(UploadRequest request, StreamObserver<UploadResponse> responseObserver) {
        MasterComm.getNodeForUpload(request, responseObserver);
    }

    @Override
    public void getNodeForDownload(DownloadRequest request, StreamObserver<DownloadResponse> responseObserver) {
        MasterComm.getNodeForDownload(request, responseObserver);
    }

    private boolean isIpValid(String ip) {
        String ipRegex = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
        return Pattern.matches(ipRegex, ip);
    }
}
