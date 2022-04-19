package ru.otus.homework;

import java.util.*;

public class ATM {
    private int balance;
    private final SortedSet<ATMCell> atmCells = new TreeSet<>(Comparator.comparingInt(ATMCell::getDenomination).reversed());

    public ATM() {
        atmCells.add(new ATMCell(100, new Banknote(100)));
        atmCells.add(new ATMCell(100, new Banknote(500)));
        atmCells.add(new ATMCell(100, new Banknote(1000)));
    }

    private void updateBalance() {
        balance = 0;
        for (ATMCell atmCell : atmCells) {
            balance += atmCell.getBalance();
        }
    }

    public Set<ATMCell> getAtmCells() {
        return atmCells;
    }

    private boolean isMoneyEnough(int amountOperation) {
        updateBalance();
        return balance >= amountOperation;
    }

    public boolean executePutOperation(Queue<Cash> cash) {
        for (ATMCell atmCell : atmCells) {
            atmCell.putMoney(cash.poll().getNumber());
        }
        return true;
    }

    public boolean executeWithdrawOperation(int amountOperation) {

        if (isMoneyEnough(amountOperation)) {
            Deque<Integer> numberOfBanknotes = calculateNumberOfBanknotes(amountOperation);

            if (numberOfBanknotes.peekLast() > atmCells.last().getNumberOfBanknotes()) {
                return false;
            } else {
                for (ATMCell atmCell : atmCells) {
                    atmCell.withdrawMoney(numberOfBanknotes.pollFirst());
                }
                return true;
            }
        } else return false;
    }

    private Deque<Integer> calculateNumberOfBanknotes(int amountOperation) {
        Deque<Integer> numberOfBanknotes = new LinkedList<>();
        Iterator<ATMCell> it = atmCells.iterator();
        int numberOfBanknote;

        while (it.hasNext()) {
            ATMCell atmCell = it.next();
            if (it.hasNext()) {
                numberOfBanknote = Math.min(amountOperation / atmCell.getDenomination(), atmCell.getNumberOfBanknotes());
                amountOperation -= numberOfBanknote * atmCell.getDenomination();
            } else {
                numberOfBanknote = amountOperation / atmCell.getDenomination();
            }
            numberOfBanknotes.add(numberOfBanknote);

        }
        return numberOfBanknotes;
    }
}
