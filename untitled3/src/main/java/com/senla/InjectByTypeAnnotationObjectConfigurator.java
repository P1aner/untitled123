package com.senla;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectCongigurator{

    @Override
    @SneakyThrows
    public void configurer(Object t, ApplictionContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)){
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
