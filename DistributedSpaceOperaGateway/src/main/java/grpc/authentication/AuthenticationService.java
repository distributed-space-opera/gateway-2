package grpc.authentication;

import io.grpc.stub.StreamObserver;
import org.gateway.protos.*;

public class AuthenticationService extends AuthenticateGrpc.AuthenticateImplBase {
    @Override
    public void register(Request request, StreamObserver<Reply> responseObserver) {
        String name = request.getName();
        String password = request.getPassword();
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginReply> responseObserver) {
        String ip = request.getClientIp();
        String password = request.getPassword();

        LoginReply.Builder loginReply = LoginReply.newBuilder();

        loginReply.setMsg("Logged In Successfully");
        loginReply.setToken("");

        responseObserver.onNext(loginReply.build());
        responseObserver.onCompleted();
    }
}
