package ru.otus;


public class TestStarter {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestService.run(TestClass.class);
    }
}
