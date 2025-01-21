
# gRPC Demo

This is a simple demonstration of a gRPC service using Spring Boot. The project includes a gRPC server that provides a `sayHello` service, which returns a greeting message based on the provided name. This project demonstrates the integration of gRPC with Spring Boot, including server and client implementation.

## Features

- Spring Boot integration with gRPC.
- A simple `HelloService` that greets the user.
- Client-side gRPC request to interact with the server.

## Prerequisites

- Java 17 or higher
- Gradle 7.0 or higher
- Protocol Buffers (protoc) compiler

## Getting Started

### 1. Clone the repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/silverhyuk/grpc-demo.git
cd grpc-demo
```

### 2. Build the project

Run the following Gradle command to build the project:

```bash
./gradlew build
```

### 3. Run the gRPC server

To start the Spring Boot application and run the gRPC server, execute the following command:

```bash
./gradlew bootRun
```

This will start the server on `localhost:50051` by default.

### 4. Test the service

The project includes both a gRPC server and a client to interact with it. You can test the service using the provided `GrpcClient` class or by using a tool like `grpcurl` or Postman.

#### Using the `GrpcClient`:

Run the client by executing the `GrpcClient.java` file. It sends a `sayHello` request to the server with the name "David":

```java
package com.david.grpcdemo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.david.grpcdemo.HelloServiceGrpc;
import com.david.grpcdemo.HelloRequest;
import com.david.grpcdemo.HelloResponse;

public class GrpcClient {

    public static void main(String[] args) {
        // Server address and port
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        // gRPC client creation
        HelloServiceGrpc.HelloServiceBlockingStub blockingStub = HelloServiceGrpc.newBlockingStub(channel);

        // Create request object
        HelloRequest request = HelloRequest.newBuilder()
                .setName("David")
                .build();

        // Send request to the server
        HelloResponse response = blockingStub.sayHello(request);

        // Print the response
        System.out.println("Received: " + response.getMessage());

        // Shutdown the channel
        channel.shutdown();
    }
}
```

#### Using `grpcurl` (Optional):

If you have `grpcurl` installed, you can test the service from the command line. Run the following command to send a `sayHello` request:

```bash
grpcurl -d '{"name": "David"}' -plaintext localhost:50051 com.david.grpcdemo.HelloService/sayHello
```

### 5. API Definition

The `HelloService` is defined using Protocol Buffers (`.proto` file). Here is the definition of the `sayHello` method:

```proto
syntax = "proto3";

package com.david.grpcdemo;

service HelloService {
    rpc sayHello (HelloRequest) returns (HelloResponse);
}

message HelloRequest {
    string name = 1;
}

message HelloResponse {
    string message = 1;
}
```

### 6. Protocol Buffers

Protocol Buffers (`.proto`) is used to define the structure of the requests and responses. The `HelloRequest` contains the `name` field, and the `HelloResponse` contains the `message` field, which is returned as a greeting.

### 7. Notes

- The server is configured to run on port `50051`. If you want to change the port, you can modify the `application.yml` file.
- You can modify the `GrpcClient` or create a new client to send different requests to the gRPC server.
- The client and server both use the same `.proto` file for defining the service and message types.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```