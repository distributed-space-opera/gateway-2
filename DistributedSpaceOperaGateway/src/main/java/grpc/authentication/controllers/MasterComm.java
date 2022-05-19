package grpc.authentication.controllers;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.gateway.protos.DownloadRequest;
import org.gateway.protos.DownloadResponse;
import org.gateway.protos.UploadRequest;
import org.gateway.protos.UploadResponse;
import org.master.protos.*;

public class MasterComm {
    public static void getNodeForDownload(DownloadRequest request,
                                          StreamObserver<DownloadResponse> responseObserver, String masterIP, int port) {
        System.out.println("---------In Node DOwnload" + masterIP + port);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(masterIP, port).usePlaintext().build();
        ReplicationGrpc.ReplicationBlockingStub stub = ReplicationGrpc.newBlockingStub(channel);

        GetNodeForDownloadRequest.Builder builder = GetNodeForDownloadRequest.newBuilder();
        builder.setFilename(request.getFilename());
        GetNodeForDownloadResponse response;
        String nodeIP = "";
        try {
            response = stub.getNodeForDownload(builder.build());
             nodeIP = response.getNodeip();
        }catch (Error e) {
            System.out.println(e.toString());
        }

        DownloadResponse.Builder clientResponseBuilder = DownloadResponse.newBuilder();

        clientResponseBuilder.setNodeip(nodeIP);

        responseObserver.onNext(clientResponseBuilder.build());
        responseObserver.onCompleted();

        channel.shutdown();

    }

    public static void getNodeForUpload(UploadRequest request,
                                        StreamObserver<UploadResponse> responseObserver, String masterIP, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(masterIP, port).usePlaintext().build();
        ReplicationGrpc.ReplicationBlockingStub stub = ReplicationGrpc.newBlockingStub(channel);

        GetNodeForUploadRequest.Builder builder = GetNodeForUploadRequest.newBuilder();
        builder.setFilename(request.getFilename());

        GetNodeForUploadResponse response = stub.getNodeForUpload(builder.build());
        String nodeIP = response.getNodeip();

        UploadResponse.Builder clientResponseBuilder = UploadResponse.newBuilder();

        clientResponseBuilder.setNodeip(nodeIP);
        clientResponseBuilder.setMessage("SUCCESS");

        System.out.println("---------NODE IP" + nodeIP);
        responseObserver.onNext(clientResponseBuilder.build());
        responseObserver.onCompleted();

        channel.shutdown();

    }

    public static Status notifyMaster(String nodeIP, String masterIP, int port) {
        System.out.println("-------IN New Node Update");
        ManagedChannel channel = ManagedChannelBuilder.forAddress(masterIP, port).usePlaintext().build();
        ReplicationGrpc.ReplicationBlockingStub stub = ReplicationGrpc.newBlockingStub(channel);

        NewNodeUpdateRequest.Builder builder = NewNodeUpdateRequest.newBuilder();

        builder.setNewnodeip(nodeIP);

        StatusResponse response = stub.newNodeUpdate(builder.build());
        System.out.println("-------IN New Node Update" + response.getStatus());

        channel.shutdown();

        return response.getStatus();

    }

}
