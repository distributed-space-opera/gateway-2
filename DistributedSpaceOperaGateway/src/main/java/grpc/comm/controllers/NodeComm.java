package grpc.comm.controllers;

import grpc.comm.db.Connector;
import grpc.comm.db.NodeDetails;
import io.grpc.stub.StreamObserver;
import org.master.protos.NewNodeUpdateRequest;
import org.master.protos.Status;
import org.master.protos.StatusResponse;

import java.sql.Connection;
import java.sql.SQLException;

public class NodeComm {

    public static void newNodeUpdate(NewNodeUpdateRequest request, StreamObserver<StatusResponse> responseObserver) {

        String nodeIP = request.getNewnodeip();
        String password = "test1234";

        StatusResponse.Builder response = StatusResponse.newBuilder();

        try {
            if (NodeDetails.checkIfNew(nodeIP)) {
                NodeDetails.addNewNode(nodeIP, password);
                response.setStatus(Status.FAILURE);
            } else {
                response.setStatus(Status.FAILURE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Status.FAILURE);
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}
