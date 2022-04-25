package ru.otus.processor;

import java.time.LocalDateTime;

public class DataTimeProviderImpl implements DataTimeProvider {
    @Override
    public int getSecond() {
        return LocalDateTime.now().getSecond();
    }
}
