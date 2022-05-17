package grpc.comm.service;

import grpc.comm.controllers.MasterComm;
import grpc.comm.controllers.NodeComm;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.master.protos.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReplicationServiceImpl extends ReplicationGrpc.ReplicationImplBase {

    private Server svr;

    public static void main(String[] args) throws Exception {
        String path = args[0];
        try {
            Properties conf = ReplicationServiceImpl.getConfiguration(new File(path));
            ReplicationServer.configure(conf);

            final ReplicationServiceImpl impl = new ReplicationServiceImpl();
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
        NodeComm.newNodeUpdate(request, responseObserver);
    }

    @Override
    public void getNodeForDownload(GetNodeForDownloadRequest request,
                                   StreamObserver<GetNodeForDownloadResponse> responseObserver) {
        MasterComm.getNodeForDownload(request, responseObserver);
    }

    @Override
    public void getNodeForUpload(GetNodeForUploadRequest request,
                                 StreamObserver<GetNodeForUploadResponse> responseObserver) {
        MasterComm.getNodeForUpload(request, responseObserver);
    }
}
