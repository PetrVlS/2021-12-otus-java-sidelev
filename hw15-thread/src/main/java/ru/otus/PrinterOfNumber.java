package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class PrinterOfNumber {
    public static final Logger logger = LoggerFactory.getLogger(PrinterOfNumber.class);
    private int param = 0;
    private String lastThread = "second";
    private final Queue<Integer> queue = new LinkedList<>();

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
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (lastThread.equals(numberOfThread)) {
                    this.wait();
                }

                lastThread = numberOfThread;
                checkAndCompleteQueue();

                if (queue.peek() == param) {
                    queue.poll();
                } else {
                    param = queue.peek();
                }

                logger.info("{}, param: {}", Thread.currentThread().getName(), param);
                Thread.sleep(500);
                notifyAll();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void checkAndCompleteQueue() {
        if (queue.isEmpty()) {
            queue.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2));
        }
    }
}
