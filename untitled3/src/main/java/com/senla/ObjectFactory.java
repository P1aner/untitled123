package com.senla;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;
    private List<ObjectCongigurator> congigurators = new ArrayList<>();

    public static ObjectFactory getInstance() {
        return ourInstance;
    }
@SneakyThrows
    private ObjectFactory() {
        config = new JavaConfig("com.senla", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));
        for (Class<? extends ObjectCongigurator> aClass : config.getScanner().getSubTypesOf(ObjectCongigurator.class)) {
            congigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();
        //todo
        congigurators.forEach(objectCongigurator -> objectCongigurator.configurer(t));

        return t;
    }
}
