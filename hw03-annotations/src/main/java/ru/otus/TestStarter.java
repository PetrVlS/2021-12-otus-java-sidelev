package ru.otus;

import java.lang.reflect.InvocationTargetException;

public class TestStarter {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        TestService.run(TestClass.class);
    }
}
