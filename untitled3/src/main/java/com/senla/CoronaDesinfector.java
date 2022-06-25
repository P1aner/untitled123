package com.senla;

public class CoronaDesinfector {
    private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    private Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

    public void start(Room room) {
        announcer.announce("starting desinfections, all out!");
        policeman.makePeopleLeaveRomm();
        desinfect(room);
        announcer.announce("try get in");
    }
    private void desinfect(Room room) {
        System.out.println();
    }

}
