package ru.otus.protobuf.server;

import io.grpc.ServerBuilder;
import ru.otus.protobuf.service.NumbersServiceImpl;

import java.io.IOException;

public class NumberServer {
    private static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws IOException, InterruptedException {
        var numbersService = new NumbersServiceImpl();

        var server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(numbersService)
                .build();

        server.start();
        System.out.println("server started, listening on port: " + SERVER_PORT);
        server.awaitTermination();

    }
}
