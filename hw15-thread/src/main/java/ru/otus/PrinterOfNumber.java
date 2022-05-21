package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PrinterOfNumber {
    public static final Logger logger = LoggerFactory.getLogger(PrinterOfNumber.class);
    private int start = 1;
    private boolean isUpwardMovement = true;

    public static void main(String[] args) {
        new PrinterOfNumber().go();
    }

    private void go() {

        var thread1 = new Thread(this::printSequenceOfNumber);
        thread1.setName("Thread-1");
        thread1.start();

        while (!thread1.isAlive()) {
        }

        var thread2 = new Thread(this::printSequenceOfNumber);
        thread2.setName("Thread-2");
        thread2.start();
    }

    private synchronized void printSequenceOfNumber() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int param;
                notifyAll();
                Thread.sleep(500);
                logger.info("{}", Thread.currentThread().getName() + ", param:" + start);
                param = start;
                this.wait();
                incrementOrDecrementNumber(param);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void incrementOrDecrementNumber(int param) {
        if (start == param) {
            if (isUpwardMovement) {
                start++;
                if (start == 10) {
                    isUpwardMovement = false;
                }
            } else {
                start--;
                if (start == 1) {
                    isUpwardMovement = true;
                }
            }
        }
    }
}
