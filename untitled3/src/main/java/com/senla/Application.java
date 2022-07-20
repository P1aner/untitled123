package com.senla;

import java.util.Map;

public class Application {
    public static ApplictionContext run(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplictionContext context = new ApplictionContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        //todo init all singletons
        context.setFactory(objectFactory);
        return context;
    }
}

