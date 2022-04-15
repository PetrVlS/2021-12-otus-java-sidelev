package ru.otus.homework;

public record Banknote(int denomination) {

    public int getDenomination() {
        return denomination;
    }
}
