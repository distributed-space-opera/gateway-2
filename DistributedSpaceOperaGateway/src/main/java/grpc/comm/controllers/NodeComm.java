package grpc.comm.controllers;

import grpc.db.NodeDetails;
import io.grpc.stub.StreamObserver;
import org.master.protos.NewNodeUpdateRequest;
import org.master.protos.Status;
import org.master.protos.StatusResponse;

public class NodeComm {

    public static void newNodeUpdate(NewNodeUpdateRequest request, StreamObserver<StatusResponse> responseObserver) {

        String nodeIP = request.getNewnodeip();
        String password = "test1234";

        StatusResponse.Builder response = StatusResponse.newBuilder();

        if (NodeDetails.checkIfNew(nodeIP)) {
            NodeDetails.addNewNode(nodeIP, password);
            response.setStatus(Status.FAILURE);
        } else {
            response.setStatus(Status.FAILURE);
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}
