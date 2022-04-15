package ru.otus.homework;

public class Demo {
    public static void main(String[] args) {
        TestLoggingInterface testLoggingClass = Ioc.createTestLoggingClass();
        testLoggingClass.calculation(1);
        testLoggingClass.calculation(1,2);
        testLoggingClass.calculation(1,2, "3.4f");
    }
}



