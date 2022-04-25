package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorException implements Processor {

    private final DataTimeProvider dataTimeProvider;

    public ProcessorException(DataTimeProvider dataTimeProvider) {
        this.dataTimeProvider = dataTimeProvider;
    }

    @Override
    public Message process(Message message) {
        int localTime = dataTimeProvider.getSecond();

        if (localTime % 2 == 0) {
            throw new RuntimeException();
        }
        return message;
    }
}
