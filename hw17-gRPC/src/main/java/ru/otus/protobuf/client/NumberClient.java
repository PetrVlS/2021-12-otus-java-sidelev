package ru.otus.protobuf.client;

import io.grpc.ManagedChannelBuilder;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.NumbersServiceGrpc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NumberClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;
    private static long currentValue = 0;
    private static int numberOfIteration = 0;
    private static final int END_OF_RANGE = 50;

    public static void main(String[] args) {
        var channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
        var stub = NumbersServiceGrpc.newStub(channel);
        var numberRequest = NumberRequest.newBuilder().setFirstValue(0).setLastValue(30).build();
        var clientResponseObserver = new ClientResponseObserver();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            numberOfIteration++;
            System.out.println("number of iteration: " + numberOfIteration);
            var lastValueFromServer = clientResponseObserver.getAndResetLastValue();
            currentValue = currentValue + lastValueFromServer + 1;
            System.out.printf("lastValueFromServer: %d, currentValue: %d \n", lastValueFromServer, currentValue);
            if (numberOfIteration == END_OF_RANGE) {
                scheduledExecutorService.shutdown();
            }
        };

        stub.getSequenceOfNumber(numberRequest, clientResponseObserver);
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
        channel.shutdown();
    }
}
