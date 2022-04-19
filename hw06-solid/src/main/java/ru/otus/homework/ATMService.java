package ru.otus.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static ru.otus.homework.Operation.*;

public class ATMService {
    private final ATM atm;
    private Operation operation;

    public ATMService(ATM atm) {
        this.atm = atm;

    }

    public void run() throws IOException {
        sayHello();
        do {
            suggestOperation();
            setOperation();
            switch (operation) {
                case PUT -> executePutOperation();
                case WITHDRAW -> executeWithdrawOperation();
            }
        } while (operation != EXIT);
        sayGoodBay();
    }

    private void sayHello() {
        System.out.println("Добрый день!");
    }

    private void suggestOperation() {
        System.out.println("Пожалуйста выберите действие (нажмите 1, 2 или 3):");
        System.out.println("1. Положить деньги");
        System.out.println("2. Снять деньги");
        System.out.println("3. Выйти");
    }

    private void setOperation() throws IOException {
        while (true) {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            var strReader = reader.readLine();
            switch (strReader) {
                case "1" -> {
                    operation = PUT;
                    return;
                }
                case "2" -> {
                    operation = WITHDRAW;
                    return;
                }
                case "3" -> {
                    operation = EXIT;
                    return;
                }
                default -> System.out.println("Введена некорректная команда. Повторите попытку");
            }
        }
    }

    private void executePutOperation() throws IOException {
        Queue<Cash> cash = setAndGetCash();
        var result = atm.executePutOperation(cash);
        sayResult(result);
    }

    private Queue<Cash> setAndGetCash() throws IOException {
        Set<ATMCell> atmCells = atm.getAtmCells();
        Queue<Cash> cash = new LinkedList<>();
        int numberOfBanknotes;

        for (ATMCell atmCell : atmCells) {
            System.out.println("Пожалуйста введите количество купюр номиналом: " + atmCell.getDenomination());
            var reader = new BufferedReader(new InputStreamReader(System.in));
            numberOfBanknotes = Integer.parseInt(reader.readLine());
            cash.add(new Cash(atmCell.getBanknote(), numberOfBanknotes));
        }
        return cash;
    }

    private void executeWithdrawOperation() throws IOException {
        var amountOperation = setAmountOperation();
        var result = atm.executeWithdrawOperation(amountOperation);

        sayResult(result);
    }

    private int setAmountOperation() throws IOException {
        System.out.println("Пожалуйста введите сумму кратную 100");
        var reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }

    private void sayResult(boolean isOperationCompletedSuccessfully) {
        if (isOperationCompletedSuccessfully) {
            System.out.println("Операция выполнена");
        } else System.out.println("Ошибка! Операция не может быть выполнена");
    }

    private void sayGoodBay() {
        System.out.println("Спасибо, что воспользовались нашим банкоматом. До свидания!");
    }
}
