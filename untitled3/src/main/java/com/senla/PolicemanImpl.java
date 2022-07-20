package com.senla;

import javax.annotation.PostConstruct;

public class PolicemanImpl implements Policeman{
    @InjectByType
    private Recomendator recomendator;

    @PostConstruct
    public void init(){
        System.out.println(recomendator.getClass());
    }

    @Override
    public void makePeopleLeaveRomm() {
        System.out.println("pif paf");
    }
}
