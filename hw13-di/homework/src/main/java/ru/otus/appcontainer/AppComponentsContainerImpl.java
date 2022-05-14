package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws ReflectiveOperationException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws ReflectiveOperationException {
        checkConfigClass(configClass);
        var methods = getOrderedMethods(configClass);
        var constructor = configClass.getDeclaredConstructor();
        var configClazz = constructor.newInstance();

        for (Method method : methods) {
            var annotation = method.getAnnotation(AppComponent.class);
            var parameters = method.getParameters();
            var args = initParameters(parameters);
            var resultInvokeMethod = method.invoke(configClazz, args);
            appComponents.add(resultInvokeMethod);
            appComponentsByName.put(annotation.name(), resultInvokeMethod);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        C component = null;
        for (Object appComponent : appComponents) {
            if (componentClass.isAssignableFrom(appComponent.getClass())) {
                component = (C) appComponent;
                break;
            }
        }
        checkComponent(component);
        return component;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        var component = (C) appComponentsByName.get(componentName);
        checkComponent(component);
        return component;
    }

    private <C> void checkComponent(C component) {
        if (component == null) {
            throw new IllegalArgumentException("Component not found");
        }
    }

    private Object[] initParameters(Parameter[] parameters) throws ReflectiveOperationException {
        var args = new Object[parameters.length];
        for (int j = 0; j < parameters.length; j++) {
                var componentClass = Class.forName(parameters[j].getAnnotatedType().toString());
                var parameter = getAppComponent(componentClass);
                args[j] = parameter;
        }
        return args;
    }

    private List<Method> getOrderedMethods(Class<?> configClass) {
        var methods = configClass.getDeclaredMethods();
        return Arrays.stream(methods).sorted(Comparator.comparing(method -> method.getAnnotation(AppComponent.class).order())).toList();
    }
}
