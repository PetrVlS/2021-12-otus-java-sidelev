package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.config.AppConfig;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws InvocationTargetException, IllegalAccessException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws InvocationTargetException, IllegalAccessException {
        checkConfigClass(configClass);
        var methods = getOrderedMethods(configClass);

        for (Method method : methods) {
            var annotation = method.getAnnotation(AppComponent.class);
            Object resultInvokeMethod;
            var parameters = method.getParameters();
            if (parameters.length != 0) {
                var args = initializationParameters(parameters);
                resultInvokeMethod = method.invoke(new AppConfig(), args);
            } else {
                resultInvokeMethod = method.invoke(new AppConfig());
            }
            fillAppComponents(resultInvokeMethod, annotation, method);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        var clazzName = componentClass.getName();
        return (C) appComponentsByName.get(clazzName);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

    private Object[] initializationParameters(Parameter[] parameters) {
        var args = new Object[parameters.length];
        for (int j = 0; j < parameters.length; j++) {
            args[j] = getAppComponent(parameters[j].getAnnotatedType().toString());
        }
        return args;
    }

    private void fillAppComponents(Object resultInvokeMethod, AppComponent annotation, Method method) {
        appComponentsByName.put(annotation.name(), resultInvokeMethod);
        appComponentsByName.put(method.getAnnotatedReturnType().toString(), resultInvokeMethod);
        appComponentsByName.put(resultInvokeMethod.getClass().getName(), resultInvokeMethod);
        var interfaces = method.getReturnType().getAnnotatedInterfaces();
        if (interfaces.length > 0) {
            for (AnnotatedType annotatedType : interfaces) {
                appComponentsByName.put(annotatedType.toString(), resultInvokeMethod);
            }
        }
    }

    private List<Method> getOrderedMethods(Class<?> configClass){
        var methods = configClass.getDeclaredMethods();
        return Arrays.stream(methods).sorted(Comparator.comparing(method -> method.getAnnotation(AppComponent.class).order())).toList();
    }
}
