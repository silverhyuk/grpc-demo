package com.david.grpcdemo;

import com.david.grpcdemo.Hello.HelloRequest;
import com.david.grpcdemo.Hello.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcDemoClient {
    public static void main(String[] args) {
        // 서버 주소와 포트
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build();

        // gRPC 클라이언트 생성
        HelloServiceGrpc.HelloServiceBlockingStub blockingStub = HelloServiceGrpc.newBlockingStub(channel);

        // 요청 객체 생성
        HelloRequest request = HelloRequest.newBuilder()
            .setName("World")
            .build();

        // 서버 호출
        HelloResponse response = blockingStub.sayHello(request);

        // 응답 출력
        System.out.println("Received: " + response.getMessage());

        // 채널 종료
        channel.shutdown();
    }
}
