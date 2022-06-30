package com.senla;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationObjectCongiguratorImpl implements ObjectCongigurator {
    Map<Object, Object> propertiesMap;
    @SneakyThrows
    public InjectPropertyAnnotationObjectCongiguratorImpl() {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void configurer(Object t) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperTy annotation = field.getAnnotation(InjectProperTy.class);



            if (annotation != null) {
                String value;
                if (annotation.value().isEmpty()) {
                    value = (String) propertiesMap.get(field.getName());
                } else {
                    value = (String) propertiesMap.get(annotation.value());
                }
                field.setAccessible(true);
                field.set(t,value);
            }
        }
    }
}
