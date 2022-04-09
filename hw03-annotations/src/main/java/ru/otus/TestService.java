package ru.otus;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestService {
    public static void run(Class<?> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        new TestService().start(clazz);
    }

    private void start(Class<?> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Method beforeMethod = getMethodByAnnotationType(clazz, Before.class);
        Method afterMethod = getMethodByAnnotationType(clazz, After.class);
        List<Method> testMethods = getMethodsByAnnotationType(clazz, Test.class);
        int numberOfTests = testMethods.size();
        int numberOfPassedTests = 0;
        int numberOfFailedTests = 0;
        TestClass testClass;

        for (Method testMethod : testMethods) {
            try {
                testClass = createTestClassObject(clazz);
                callMethod(beforeMethod, testClass);
                callMethod(testMethod, testClass);
                callMethod(afterMethod, testClass);
                numberOfPassedTests++;
            } catch (InvocationTargetException e) {
                System.out.println(testMethod.getName() + " failed");
                numberOfFailedTests++;
            }
        }

        printResult(numberOfTests, numberOfPassedTests, numberOfFailedTests);
    }

    private List<Method> getMethodsByAnnotationType(Class<?> clazz, Class<? extends Annotation> annotation){
        Method[] metods =clazz.getDeclaredMethods();
        List<Method> returnMetods = new ArrayList<>();
        for (Method metod: metods) {
            if (metod.isAnnotationPresent(annotation)){
                returnMetods.add(metod);
            }
        }
        return returnMetods;
    }

    private Method getMethodByAnnotationType(Class<?> clazz, Class<? extends Annotation> annotation){
        Method[] metods =clazz.getDeclaredMethods();

        for (Method metod: metods) {
            if (metod.isAnnotationPresent(annotation)){
                return metod;
            }
        }
        return null;
    }

    private TestClass createTestClassObject(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<TestClass> constructor = (Constructor<TestClass>) clazz.getConstructor();
        return  constructor.newInstance();
    }

    private void callMethod(Method method, TestClass testClass) throws InvocationTargetException, IllegalAccessException {
        if(method != null)
            method.invoke(testClass);
    }

    private void printResult(int numberOfTests, int numberOfPassedTests, int numberOfFailedTests){
        System.out.println("Результат тестов: из " + numberOfTests + " успешно пройдено " + numberOfPassedTests+ ", упало " + numberOfFailedTests);
    }
}
