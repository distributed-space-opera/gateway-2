package org.master.protos;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.0)",
    comments = "Source: master-comm.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ReplicationGrpc {

  private ReplicationGrpc() {}

  public static final String SERVICE_NAME = "stream.Replication";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.master.protos.NewNodeUpdateRequest,
      org.master.protos.StatusResponse> getNewNodeUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NewNodeUpdate",
      requestType = org.master.protos.NewNodeUpdateRequest.class,
      responseType = org.master.protos.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.master.protos.NewNodeUpdateRequest,
      org.master.protos.StatusResponse> getNewNodeUpdateMethod() {
    io.grpc.MethodDescriptor<org.master.protos.NewNodeUpdateRequest, org.master.protos.StatusResponse> getNewNodeUpdateMethod;
    if ((getNewNodeUpdateMethod = ReplicationGrpc.getNewNodeUpdateMethod) == null) {
      synchronized (ReplicationGrpc.class) {
        if ((getNewNodeUpdateMethod = ReplicationGrpc.getNewNodeUpdateMethod) == null) {
          ReplicationGrpc.getNewNodeUpdateMethod = getNewNodeUpdateMethod =
              io.grpc.MethodDescriptor.<org.master.protos.NewNodeUpdateRequest, org.master.protos.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NewNodeUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.NewNodeUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReplicationMethodDescriptorSupplier("NewNodeUpdate"))
              .build();
        }
      }
    }
    return getNewNodeUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.master.protos.GetNodeForDownloadRequest,
      org.master.protos.GetNodeForDownloadResponse> getGetNodeForDownloadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNodeForDownload",
      requestType = org.master.protos.GetNodeForDownloadRequest.class,
      responseType = org.master.protos.GetNodeForDownloadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.master.protos.GetNodeForDownloadRequest,
      org.master.protos.GetNodeForDownloadResponse> getGetNodeForDownloadMethod() {
    io.grpc.MethodDescriptor<org.master.protos.GetNodeForDownloadRequest, org.master.protos.GetNodeForDownloadResponse> getGetNodeForDownloadMethod;
    if ((getGetNodeForDownloadMethod = ReplicationGrpc.getGetNodeForDownloadMethod) == null) {
      synchronized (ReplicationGrpc.class) {
        if ((getGetNodeForDownloadMethod = ReplicationGrpc.getGetNodeForDownloadMethod) == null) {
          ReplicationGrpc.getGetNodeForDownloadMethod = getGetNodeForDownloadMethod =
              io.grpc.MethodDescriptor.<org.master.protos.GetNodeForDownloadRequest, org.master.protos.GetNodeForDownloadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNodeForDownload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.GetNodeForDownloadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.GetNodeForDownloadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReplicationMethodDescriptorSupplier("GetNodeForDownload"))
              .build();
        }
      }
    }
    return getGetNodeForDownloadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.master.protos.GetNodeForUploadRequest,
      org.master.protos.GetNodeForUploadResponse> getGetNodeForUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNodeForUpload",
      requestType = org.master.protos.GetNodeForUploadRequest.class,
      responseType = org.master.protos.GetNodeForUploadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.master.protos.GetNodeForUploadRequest,
      org.master.protos.GetNodeForUploadResponse> getGetNodeForUploadMethod() {
    io.grpc.MethodDescriptor<org.master.protos.GetNodeForUploadRequest, org.master.protos.GetNodeForUploadResponse> getGetNodeForUploadMethod;
    if ((getGetNodeForUploadMethod = ReplicationGrpc.getGetNodeForUploadMethod) == null) {
      synchronized (ReplicationGrpc.class) {
        if ((getGetNodeForUploadMethod = ReplicationGrpc.getGetNodeForUploadMethod) == null) {
          ReplicationGrpc.getGetNodeForUploadMethod = getGetNodeForUploadMethod =
              io.grpc.MethodDescriptor.<org.master.protos.GetNodeForUploadRequest, org.master.protos.GetNodeForUploadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNodeForUpload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.GetNodeForUploadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.GetNodeForUploadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReplicationMethodDescriptorSupplier("GetNodeForUpload"))
              .build();
        }
      }
    }
    return getGetNodeForUploadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReplicationStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplicationStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplicationStub>() {
        @java.lang.Override
        public ReplicationStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplicationStub(channel, callOptions);
        }
      };
    return ReplicationStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReplicationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplicationBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplicationBlockingStub>() {
        @java.lang.Override
        public ReplicationBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplicationBlockingStub(channel, callOptions);
        }
      };
    return ReplicationBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReplicationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplicationFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplicationFutureStub>() {
        @java.lang.Override
        public ReplicationFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplicationFutureStub(channel, callOptions);
        }
      };
    return ReplicationFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ReplicationImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Methods required for Gateway
     * </pre>
     */
    public void newNodeUpdate(org.master.protos.NewNodeUpdateRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNewNodeUpdateMethod(), responseObserver);
    }

    /**
     */
    public void getNodeForDownload(org.master.protos.GetNodeForDownloadRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.GetNodeForDownloadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNodeForDownloadMethod(), responseObserver);
    }

    /**
     */
    public void getNodeForUpload(org.master.protos.GetNodeForUploadRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.GetNodeForUploadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNodeForUploadMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNewNodeUpdateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.master.protos.NewNodeUpdateRequest,
                org.master.protos.StatusResponse>(
                  this, METHODID_NEW_NODE_UPDATE)))
          .addMethod(
            getGetNodeForDownloadMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.master.protos.GetNodeForDownloadRequest,
                org.master.protos.GetNodeForDownloadResponse>(
                  this, METHODID_GET_NODE_FOR_DOWNLOAD)))
          .addMethod(
            getGetNodeForUploadMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.master.protos.GetNodeForUploadRequest,
                org.master.protos.GetNodeForUploadResponse>(
                  this, METHODID_GET_NODE_FOR_UPLOAD)))
          .build();
    }
  }

  /**
   */
  public static final class ReplicationStub extends io.grpc.stub.AbstractAsyncStub<ReplicationStub> {
    private ReplicationStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplicationStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplicationStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods required for Gateway
     * </pre>
     */
    public void newNodeUpdate(org.master.protos.NewNodeUpdateRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNewNodeUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNodeForDownload(org.master.protos.GetNodeForDownloadRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.GetNodeForDownloadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNodeForDownloadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNodeForUpload(org.master.protos.GetNodeForUploadRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.GetNodeForUploadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNodeForUploadMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ReplicationBlockingStub extends io.grpc.stub.AbstractBlockingStub<ReplicationBlockingStub> {
    private ReplicationBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplicationBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplicationBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods required for Gateway
     * </pre>
     */
    public org.master.protos.StatusResponse newNodeUpdate(org.master.protos.NewNodeUpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNewNodeUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.master.protos.GetNodeForDownloadResponse getNodeForDownload(org.master.protos.GetNodeForDownloadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNodeForDownloadMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.master.protos.GetNodeForUploadResponse getNodeForUpload(org.master.protos.GetNodeForUploadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNodeForUploadMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReplicationFutureStub extends io.grpc.stub.AbstractFutureStub<ReplicationFutureStub> {
    private ReplicationFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplicationFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplicationFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods required for Gateway
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.master.protos.StatusResponse> newNodeUpdate(
        org.master.protos.NewNodeUpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getNewNodeUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.master.protos.GetNodeForDownloadResponse> getNodeForDownload(
        org.master.protos.GetNodeForDownloadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNodeForDownloadMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.master.protos.GetNodeForUploadResponse> getNodeForUpload(
        org.master.protos.GetNodeForUploadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNodeForUploadMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NEW_NODE_UPDATE = 0;
  private static final int METHODID_GET_NODE_FOR_DOWNLOAD = 1;
  private static final int METHODID_GET_NODE_FOR_UPLOAD = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReplicationImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReplicationImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NEW_NODE_UPDATE:
          serviceImpl.newNodeUpdate((org.master.protos.NewNodeUpdateRequest) request,
              (io.grpc.stub.StreamObserver<org.master.protos.StatusResponse>) responseObserver);
          break;
        case METHODID_GET_NODE_FOR_DOWNLOAD:
          serviceImpl.getNodeForDownload((org.master.protos.GetNodeForDownloadRequest) request,
              (io.grpc.stub.StreamObserver<org.master.protos.GetNodeForDownloadResponse>) responseObserver);
          break;
        case METHODID_GET_NODE_FOR_UPLOAD:
          serviceImpl.getNodeForUpload((org.master.protos.GetNodeForUploadRequest) request,
              (io.grpc.stub.StreamObserver<org.master.protos.GetNodeForUploadResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ReplicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReplicationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.master.protos.MasterComm.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Replication");
    }
  }

  private static final class ReplicationFileDescriptorSupplier
      extends ReplicationBaseDescriptorSupplier {
    ReplicationFileDescriptorSupplier() {}
  }

  private static final class ReplicationMethodDescriptorSupplier
      extends ReplicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReplicationMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ReplicationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReplicationFileDescriptorSupplier())
              .addMethod(getNewNodeUpdateMethod())
              .addMethod(getGetNodeForDownloadMethod())
              .addMethod(getGetNodeForUploadMethod())
              .build();
        }
      }
    }
    return result;
  }
}
