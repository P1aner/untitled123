package com.senla;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;


public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    private ObjectFactory() {
        config = new JavaConfig("com.senla", new HashMap<>(Map.of(Policeman.class,AngryPoliceman.class)));
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
            T t = implClass.getDeclaredConstructor().newInstance();
        //todo
        for(Field field : implClass.getDeclaredFields()){
            InjectProperTy annotation = field.getAnnotation(InjectProperTy.class);
            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();

            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            Map<Object, Object> propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr ->[0], arr ->[1]));

            if (annotation != null){

                if (annotation.value().isEmpty()){

                }
            }
        }
        return t;
    }
}
