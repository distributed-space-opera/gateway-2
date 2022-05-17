package grpc.comm.service;

import io.grpc.stub.StreamObserver;
import org.master.protos.NewNodeUpdateRequest;
import org.master.protos.StatusResponse;

import java.sql.DriverManager;

public class NodeComm {

    static void newNodeUpdate(NewNodeUpdateRequest request, StreamObserver<StatusResponse> responseObserver) {

    }

}
