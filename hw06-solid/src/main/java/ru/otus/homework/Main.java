package ru.otus.homework;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var atmService = new ATMService(new ATMNameBank());
        atmService.run();
    }
}
