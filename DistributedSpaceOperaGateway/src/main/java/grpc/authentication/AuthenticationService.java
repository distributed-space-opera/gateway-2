package grpc.authentication;

import grpc.authentication.controllers.MasterComm;
import grpc.db.ClientDetails;
import grpc.db.Connector;
import grpc.db.NodeDetails;
import grpc.jwt.JwtHelper;
import io.grpc.stub.StreamObserver;
import org.gateway.protos.*;

public class AuthenticationService extends AuthenticateGrpc.AuthenticateImplBase {
    private enum TYPES {CLIENT, NODE};

    private JwtHelper jwtHelper;

    public AuthenticationService() {
        jwtHelper = new JwtHelper();
    }

    @Override
    public void register(Request request, StreamObserver<Reply> responseObserver) {
        String clientIp = request.getIp();
        String password = request.getPassword();
        String type = request.getType();

        Connector.QueryStatus status = Connector.QueryStatus.FAILURE;

        if (type.equalsIgnoreCase(TYPES.CLIENT.toString())) {
           status = ClientDetails.addNewClient(clientIp, password);
        }

        if (type.equalsIgnoreCase(TYPES.NODE.toString())) {
            status = NodeDetails.addNewNode(clientIp, password);
        }

        Reply.Builder registerReply = Reply.newBuilder();
        registerReply.setMessage("");
        registerReply.setMasterip("");
        registerReply.setToken("");

        if(status == Connector.QueryStatus.FAILURE) {
            registerReply.setMessage(type + " was not registered");

            responseObserver.onNext(registerReply.build());
            responseObserver.onCompleted();

            return;
        }

        registerReply.setMessage(type + " registered successfully");

        responseObserver.onNext(registerReply.build());
        responseObserver.onCompleted();
    }

    @Override
    public void login(Request request, StreamObserver<Reply> responseObserver) {
        String clientIp = request.getIp();
        String password = request.getPassword();
        String type = request.getType();

        Reply.Builder loginReply = Reply.newBuilder();
        boolean isValid = false;

        if (type.equalsIgnoreCase(TYPES.CLIENT.toString())) {
            isValid = ClientDetails.validateClient(clientIp, password);
        }

        if (type.equalsIgnoreCase(TYPES.NODE.toString())) {
            isValid = NodeDetails.validateNode(clientIp, password);
        }

        loginReply.setMessage("");
        loginReply.setToken("");
        loginReply.setMasterip("");

        if(!isValid) {
            loginReply.setMessage(type + " is not registered");

            responseObserver.onNext(loginReply.build());
            responseObserver.onCompleted();

            return;
        }

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
}
