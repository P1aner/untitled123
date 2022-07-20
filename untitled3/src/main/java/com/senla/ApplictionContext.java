package com.senla;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplictionContext {
    @Setter
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplictionContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {

        if(cache.containsKey(type)){
            return  (T) cache.get(type);
        }
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = factory.createObject(implClass);
        if (implClass.isAnnotationPresent(Singleton.class)){
            cache.put(type,t);
        }

        return t;
    }
}
