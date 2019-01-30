package com.example.demo.first.stub

import com.example.demo.grpc.HelloRequest
import com.example.demo.grpc.HelloResponse
import com.example.demo.grpc.HelloServiceGrpc
import io.grpc.stub.StreamObserver

class HelloServiceImpl : HelloServiceGrpc.HelloServiceImplBase() {
    override fun hello(request: HelloRequest?, responseObserver: StreamObserver<HelloResponse>?) {
        responseObserver?.run {
            onNext(HelloResponse.newBuilder().setGreeting("Hello ${request?.firstName} ${request?.lastName}").build())
            onCompleted()
        }
    }
}
