package com.senla;

public class RecomendatorImpl implements Recomendator{
    @InjectProperTy("visky")
    private String alcohol;

    @Override
    public void recomend() {
        System.out.println("to protect from covid drink " + alcohol);
    }
}
