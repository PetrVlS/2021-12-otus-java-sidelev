package ru.otus.homework;

public class ATMCell {

    private int numberOfBanknotes;
    private final Banknote banknote;

    public ATMCell(int numberOfBanknotes, Banknote banknote) {
        this.numberOfBanknotes = numberOfBanknotes;
        this.banknote = banknote;
    }

    public void putMoney(int numberOfBanknotes){
        this.numberOfBanknotes += numberOfBanknotes;
    }

    public void withdrawMoney(int numberOfBanknotes){
        this.numberOfBanknotes -= numberOfBanknotes;
    }

    public int getNumberOfBanknotes(){
        return numberOfBanknotes;
    }

    public int getBalance(){
        return numberOfBanknotes * banknote.getDenomination();
    }
}
