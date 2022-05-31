package ru.otus.protobuf.client;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.NumberResponse;
import ru.otus.protobuf.server.NumberServer;

public class ClientResponseObserver implements StreamObserver<ru.otus.protobuf.generated.NumberResponse> {

    private long lastValue = 0;

    @Override
    public void onNext(NumberResponse value) {
        setLastValue(value.getNumber());
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("onError");
    }

    @Override
    public void onCompleted() {
        System.out.println("onCompleted");
    }

    private synchronized void setLastValue(long value) {
        lastValue = value;
    }

    public synchronized long getAndResetLastValue() {
        var value = lastValue;
        lastValue = 0;
        return value;
    }
}
