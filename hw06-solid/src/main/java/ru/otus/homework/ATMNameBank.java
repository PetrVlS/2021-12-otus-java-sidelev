package ru.otus.homework;

public class ATMNameBank implements ATM {
    private int balance;
    private final ATMCell atmCell100 = new ATMCell(100, new Banknote(100));
    private final ATMCell atmCell500 = new ATMCell(100, new Banknote(500));
    private final ATMCell atmCell1000 = new ATMCell(100, new Banknote(1000));

    public ATMNameBank() {
        updateBalance();
    }

    public void updateBalance() {
        balance = atmCell100.getBalance() + atmCell500.getBalance() + atmCell1000.getBalance();
    }

    private boolean isMoneyEnough(int amountOperation) {
        return balance > amountOperation;
    }

    public boolean executePutOperation(int numberOfBanknotes1000, int numberOfBanknotes500, int numberOfBanknotes100) {

        atmCell1000.putMoney(numberOfBanknotes1000);
        atmCell500.putMoney(numberOfBanknotes500);
        atmCell100.putMoney(numberOfBanknotes100);
        updateBalance();

        return true;
    }

    public boolean executeWithdrawOperation(int amountOperation) {

        if (isMoneyEnough(amountOperation)) {
            var numberOfBanknotes1000 = Math.min(amountOperation / 1000, atmCell1000.getNumberOfBanknotes());
            amountOperation -= numberOfBanknotes1000 * 1000;
            var numberOfBanknotes500 = Math.min(amountOperation / 500, atmCell500.getNumberOfBanknotes());
            amountOperation -= numberOfBanknotes500 * 500;
            var numberOfBanknotes100 = amountOperation / 100;

            if (numberOfBanknotes100 > atmCell100.getNumberOfBanknotes()) {
                return false;
            } else {
                atmCell1000.withdrawMoney(numberOfBanknotes1000);
                atmCell500.withdrawMoney(numberOfBanknotes500);
                atmCell100.withdrawMoney(numberOfBanknotes100);
                updateBalance();
                return true;
            }
        } else return false;
    }
}
