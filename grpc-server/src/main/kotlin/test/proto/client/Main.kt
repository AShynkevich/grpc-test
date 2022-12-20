package test.proto.client

import com.proto.entity.hello.GreeterGrpcKt
import com.proto.entity.hello.HelloRequest
import com.proto.entity.hello.helloReply
import io.grpc.ServerBuilder

fun main() {
    val port = 25250
    val server = ServerBuilder
        .forPort(port)
        .addService(HelloService())
        .build()

    server.start()
    println("Server started at $port")
    Runtime.getRuntime().addShutdownHook(
        Thread {
            println("*** shutting down gRPC server since JVM is shutting down")
            server.shutdown()
            println("*** server shut down")
        }
    )

    server.awaitTermination()
}

internal class HelloService(): GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest) = helloReply {
        message = "Server replied to [${request.name}]"
    }
}