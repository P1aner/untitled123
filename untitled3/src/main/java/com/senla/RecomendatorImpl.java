package com.senla;
@Deprecated
@Singleton
public class RecomendatorImpl implements Recomendator{
    public RecomendatorImpl() {
      System.out.println("recomendator was created");
    }

    @InjectProperTy("visky")
    private String alcohol;

    @Override
    public void recomend() {
        System.out.println("to protect from covid drink " + alcohol);
    }
}
