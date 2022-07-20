package com.senla;

public class ConsoleAnnouncer implements Announcer{
    @InjectByType
    private Recomendator recomendator;
    @Override
    public void announce(String message) {
        System.out.println(message);
        recomendator.recomend();
    }
}
