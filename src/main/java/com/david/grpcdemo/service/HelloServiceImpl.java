package com.david.grpcdemo.service;

import com.david.grpcdemo.Hello.HelloRequest;
import com.david.grpcdemo.Hello.HelloResponse;
import com.david.grpcdemo.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);

        String greeting = "Hello, " + request.getName() + "!";
        HelloResponse response = HelloResponse.newBuilder().setMessage(greeting).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
