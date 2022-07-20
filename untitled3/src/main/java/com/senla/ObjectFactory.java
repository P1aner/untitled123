package com.senla;

import lombok.Setter;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ObjectFactory {
    private static ObjectFactory objectFactory;

    private final ApplictionContext context;

    private List<ObjectCongigurator> congigurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyCongigurators = new ArrayList<>();


    @SneakyThrows
    public ObjectFactory(ApplictionContext context) {
        this.context = context;
        for (Class<? extends ObjectCongigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectCongigurator.class)) {
            congigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyCongigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = createImpl(implClass);
        //todo
        configure(t);
        invokeInit(implClass, t);

        t = wrapWithProxyIfNeeded(implClass, t);


        return t;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator proxyCongigurator : proxyCongigurators) {
            t = (T) proxyCongigurator.replaceWithProxyIfNeeded(t, implClass);
        }
        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        congigurators.forEach(objectCongigurator -> objectCongigurator.configurer(t, context));
    }

    private <T> T createImpl(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = implClass.getDeclaredConstructor().newInstance();
        return t;
    }
}
