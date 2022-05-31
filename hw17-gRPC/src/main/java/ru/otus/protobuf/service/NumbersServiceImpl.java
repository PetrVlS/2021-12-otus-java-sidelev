package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.NumberResponse;
import ru.otus.protobuf.generated.NumbersServiceGrpc;
import ru.otus.protobuf.server.NumberServer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class NumbersServiceImpl extends NumbersServiceGrpc.NumbersServiceImplBase {

    @Override
    public void getSequenceOfNumber(NumberRequest request, StreamObserver<NumberResponse> responseObserver) {
        var firstValue = request.getFirstValue();
        var lastValue = request.getLastValue();

        var value = new AtomicLong(firstValue);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            var valueFromServer = value.incrementAndGet();
            var numberResponse = NumberResponse
                    .newBuilder()
                    .setNumber(valueFromServer)
                    .build();
            responseObserver.onNext(numberResponse);
            if (valueFromServer == lastValue) {
                responseObserver.onCompleted();
                executorService.shutdown();
            }
        };

        executorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

    }
}
