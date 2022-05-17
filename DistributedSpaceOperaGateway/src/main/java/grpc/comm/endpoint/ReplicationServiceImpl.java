package grpc.comm.endpoint;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.master.protos.*;

public class ReplicationServiceImpl extends ReplicationGrpc.ReplicationImplBase {

    private Server svr;

    public static void main(String[] args) {

    }

    private void start() throws Exception {
        svr = ServerBuilder.forPort(ReplicationServer.getInstance().getServerPort()).addService(new ReplicationServiceImpl())
                .build();

        System.out.println("-- starting server");
        svr.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                ReplicationServiceImpl.this.stop();
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
    public void newNodeUpdate(NewNodeUpdateRequest request, StreamObserver<StatusResponse> responseObserver) {
        super.newNodeUpdate(request, responseObserver);
    }

    @Override
    public void getNodeForDownload(GetNodeForDownloadRequest request, StreamObserver<GetNodeForDownloadResponse> responseObserver) {
        super.getNodeForDownload(request, responseObserver);
    }

    @Override
    public void getNodeForUpload(GetNodeForUploadRequest request, StreamObserver<GetNodeForUploadResponse> responseObserver) {
        super.getNodeForUpload(request, responseObserver);
    }
}
