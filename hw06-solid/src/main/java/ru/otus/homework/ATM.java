package ru.otus.homework;

public interface ATM {
    void updateBalance();
    boolean executePutOperation(int numberOfBanknotes1000, int numberOfBanknotes500, int numberOfBanknotes100);
    boolean executeWithdrawOperation(int amountOperation);
}
