package ru.otus.processor;

import ru.otus.model.Message;

import java.time.LocalDateTime;

public class ProcessorException implements Processor {

    DataTimeProviderImpl dataTimeProvider;

    public ProcessorException(DataTimeProviderImpl dataTimeProvider) {
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
