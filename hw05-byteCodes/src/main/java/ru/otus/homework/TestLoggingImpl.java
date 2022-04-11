package ru.otus.homework;


public class TestLoggingImpl implements TestLoggingInterface {

    @Override
    @Log
    public void calculation(int param) {}

    @Override
    public void calculation(int param1, int param2) {}

    @Override
    @Log
    public void calculation(int param1, int param2, String param3) {}

    @Override
    public String toString() {
        return "TestLoggingImpl";
    }
}
