package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {
    List<Message> history = new ArrayList<>();

    @Override
    public void onUpdated(Message msg) {
        history.add(msg.copy());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        Optional<Message> optionalMessage = history.stream().filter(message -> message.getId() == id).findFirst();
        return optionalMessage;
    }
}
