package com.senla;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);
}
