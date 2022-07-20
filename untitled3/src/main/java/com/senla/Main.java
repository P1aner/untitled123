package com.senla;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
      //  CoronaDesinfector coronaDesinfector = ObjectFactory.getInstance().createObject(CoronaDesinfector.class);
        ApplictionContext context = Application.run("com.senla", new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class)));
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);

        desinfector.start(new Room());
    }
}
