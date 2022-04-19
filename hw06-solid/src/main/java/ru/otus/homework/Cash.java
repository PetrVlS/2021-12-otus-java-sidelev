package ru.otus.homework;


public record Cash(Banknote banknote, int number) {

    public int getNumber() {
        return number;
    }

}
