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
    public void register(Request request, StreamObserver<Reply> responseObserver) {
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
            response = MasterComm.notifyMaster(clientIp, AuthenticationServer.getInstance().getMasterIP(), AuthenticationServer.getInstance().getMasterPort());
        }

        if(status == Connector.QueryStatus.FAILURE || response == Status.FAILURE) {
            registerReply.setMessage("ERROR");
            try {
                Connector.getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(registerReply.build());
            responseObserver.onCompleted();

            return;
        }

        try {
            Connector.getConnection().commit();
            registerReply.setMessage("SUCCESS");
            registerReply.setToken(jwtHelper.getToken(clientIp, password));
            registerReply.setMasterip(AuthenticationServer.getInstance().getMasterIP());
            responseObserver.onNext(registerReply.build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            e.printStackTrace();
            registerReply.setMessage("ERROR");

            responseObserver.onNext(registerReply.build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void login(Request request, StreamObserver<Reply> responseObserver) {
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
            System.out.println(isValid);
        }

        if (type.equalsIgnoreCase(TYPES.NODE.toString())) {
            isValid = NodeDetails.validateNode(clientIp, password);
            System.out.println(isValid);
        }

        if(!isValid) {
            loginReply.setMessage("ERROR");

            try {
                Connector.getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(loginReply.build());
            responseObserver.onCompleted();

            return;
        }
        try {
            Connector.getConnection().commit();
            loginReply.setMessage("SUCCESS");
            loginReply.setToken(jwtHelper.getToken(clientIp, type));
            loginReply.setMasterip(AuthenticationServer.getInstance().getMasterIP());

            responseObserver.onNext(loginReply.build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            e.printStackTrace();
            loginReply.setMessage("ERROR");

            responseObserver.onNext(loginReply.build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getNodeForUpload(UploadRequest request, StreamObserver<UploadResponse> responseObserver) {
        MasterComm.getNodeForUpload(request, responseObserver, AuthenticationServer.getInstance().getMasterIP(), AuthenticationServer.getInstance().getMasterPort());
    }

    @Override
    public void getNodeForDownload(DownloadRequest request, StreamObserver<DownloadResponse> responseObserver) {
        MasterComm.getNodeForDownload(request, responseObserver, AuthenticationServer.getInstance().getMasterIP(), AuthenticationServer.getInstance().getMasterPort());
    }

    private boolean isIpValid(String ip) {
        String ipRegex = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
        return Pattern.matches(ipRegex, ip);
    }
}
