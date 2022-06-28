package com.senla;

public class ConsoleAnnouncer implements Announcer{
    private Recomendator recomendator = ObjectFactory.getInstance().createObject();
    @Override
    public void announce(String message) {
        System.out.println(message);
    }
}
