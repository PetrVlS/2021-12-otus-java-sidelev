package ru.otus;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

public class TestClass {

    @Before
    void setUp(){
        System.out.println("@Before started");
        System.out.println("@Before passed");
    }

    @Test
    void test1(){
        System.out.println("Test №1 started");
        System.out.println("Test №1 passed");
    }

    @Test
    void test2(){
        System.out.println("Test №2 started");
        throw new RuntimeException();
    }

    @Test
    void test3(){
        System.out.println("Test №3 started");
        System.out.println("Test №3 passed");
    }

    @After
    void tearDown(){
        System.out.println("@After started");
        System.out.println("@After passed");
    }

}
