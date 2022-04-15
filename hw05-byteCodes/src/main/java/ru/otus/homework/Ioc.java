package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createTestLoggingClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingImpl());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLoggingClass;
        private final Class<?> clazz;
        private final Method[] declaredMethods;
        private final Set<Method> annotationLogPresentMethods;

        DemoInvocationHandler(TestLoggingInterface testLoggingClass) {
            this.testLoggingClass = testLoggingClass;
            clazz = testLoggingClass.getClass();
            declaredMethods = clazz.getDeclaredMethods();
            annotationLogPresentMethods = getAnnotationLogPresentMethods(declaredMethods);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            for (Method annotationLogPresentMethod: annotationLogPresentMethods) {
                if (isMethodsEquals(annotationLogPresentMethod, method)) {
                    printAutoLog(annotationLogPresentMethod, args);
                    break;
                }
            }

            return method.invoke(testLoggingClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "testLoggingClass=" + testLoggingClass +
                    '}';
        }

        private HashSet<Method> getAnnotationLogPresentMethods(Method[] methods){
            var annotationLogPresentMethods = new HashSet<Method>();

            for (Method method : methods) {
                if(method.isAnnotationPresent(Log.class))
                    annotationLogPresentMethods.add(method);
            }
            return annotationLogPresentMethods;
        }

        private boolean isNameOfMethodsEquals(Method method1, Method method2){
            return method1.getName().equals(method2.getName());
        }

        private boolean isCountParametersOfMethodsEquals(Method method1, Method method2){
            return method1.getParameterCount() == method2.getParameterCount();
        }

        private boolean isParametersOfMethodsEquals(Method method1, Method method2) {
            var parameterTypesOfMethod1 = method1.getParameterTypes();
            var parameterTypesOfMethod2 = method2.getParameterTypes();

            return Arrays.equals(parameterTypesOfMethod1, parameterTypesOfMethod2);
        }

        private boolean isMethodsEquals(Method method1, Method method2){
            return isNameOfMethodsEquals(method1, method2) &&
                    isCountParametersOfMethodsEquals(method1, method2) &&
                    isParametersOfMethodsEquals(method1, method2);
        }

        private void printAutoLog(Method method, Object[] args){
            System.out.println("executed method:" + method.getName() + ", " + Arrays.stream(method.getParameters()).toList() + " " + Arrays.stream(args).toList());
        }
    }
}
