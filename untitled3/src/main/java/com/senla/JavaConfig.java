package com.senla;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config{
    @Getter
    private Reflections scanner;
    private Map<Class, Class> ifcToImplClas;

    public JavaConfig(String packegeToScan, Map<Class, Class> ifcToImplClas) {
        this.ifcToImplClas = ifcToImplClas;
        this.scanner = new Reflections(packegeToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifcToImplClas.computeIfAbsent(ifc, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() !=1){
                throw new RuntimeException(ifc +" has 0 or more than one impl please update config");

            }
            return classes.iterator().next();
        });

    }
}
