package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PrinterOfNumber {
    public static final Logger logger = LoggerFactory.getLogger(PrinterOfNumber.class);
    private String lastThread = "second";

    public static void main(String[] args) {
        new PrinterOfNumber().go();
    }

    private void go() {

        var thread1 = new Thread(() -> printSequenceOfNumber("first"));
        thread1.setName("Thread-1");
        thread1.start();

        var thread2 = new Thread(() -> printSequenceOfNumber("second"));
        thread2.setName("Thread-2");
        thread2.start();
    }

    private synchronized void printSequenceOfNumber(String numberOfThread) {
        Counter counter = new Counter();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (lastThread.equals(numberOfThread)) {
                    this.wait();
                }
                lastThread = numberOfThread;

                logger.info("{}, param: {}", Thread.currentThread().getName(), counter.getAndChangeParam());

                Thread.sleep(500);
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
