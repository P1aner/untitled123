package com.senla;

public class CoronaDesinfector {
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

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
