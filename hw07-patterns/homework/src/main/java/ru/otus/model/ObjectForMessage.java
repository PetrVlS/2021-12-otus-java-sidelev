package ru.otus.model;

import java.util.List;

public class ObjectForMessage implements Copyable {
    private List<String> data;

    public ObjectForMessage() {

    }

    public ObjectForMessage(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public ObjectForMessage copy() {
        if (data != null) {
            return new ObjectForMessage(List.copyOf(this.data));
        } else return new ObjectForMessage();
    }

}
