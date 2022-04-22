package ru.otus.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;

import ru.otus.processor.DataTimeProviderImpl;
import ru.otus.processor.ProcessorException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.*;

public class ProcessorExceptionTest {

    @Test
    @DisplayName("Тестируем обработку исключения в четные секунды")
    void handleExceptionInSecondTest() {
        //given
        var message = new Message.Builder(1L).field8("field8").build();
        var dataTimeProvider = mock(DataTimeProviderImpl.class);

        //when
        when(dataTimeProvider.getSecond()).thenReturn(2);
        var processor = new ProcessorException(dataTimeProvider);

        //then
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> processor.process(message));

        //when
        when(dataTimeProvider.getSecond()).thenReturn(30);
        //then
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> processor.process(message));

        //when
        when(dataTimeProvider.getSecond()).thenReturn(15);
        //then
        assertThatNoException().isThrownBy(() -> processor.process(message));
    }
}
