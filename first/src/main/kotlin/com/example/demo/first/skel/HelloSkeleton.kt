package com.example.demo.first.skel

import com.example.demo.grpc.HelloGrpc
import com.example.demo.grpc.HelloRequest
import com.example.demo.grpc.HelloResponse
import io.grpc.stub.StreamObserver

class HelloSkeleton : HelloGrpc.HelloImplBase() {
    override fun hello(request: HelloRequest?, responseObserver: StreamObserver<HelloResponse>?) {
        responseObserver?.run {
            onNext(HelloResponse.newBuilder().setGreeting("Hello ${request?.firstName} ${request?.lastName}").build())
            onCompleted()
        }
    }
}
