package grpc.comm.controllers;

import io.grpc.stub.StreamObserver;
import org.master.protos.GetNodeForDownloadRequest;
import org.master.protos.GetNodeForDownloadResponse;
import org.master.protos.GetNodeForUploadRequest;
import org.master.protos.GetNodeForUploadResponse;

public class MasterComm {

    public static void getNodeForDownload(GetNodeForDownloadRequest request,
                                     StreamObserver<GetNodeForDownloadResponse> responseObserver) {

        String masterIP = "";
        int port =
//
    }

    public static void getNodeForUpload(GetNodeForUploadRequest request,
                                        StreamObserver<GetNodeForUploadResponse> responseObserver) {



    }

}
