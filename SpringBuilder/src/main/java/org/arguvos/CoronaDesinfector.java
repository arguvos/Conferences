package org.arguvos;

public class CoronaDesinfector {

    private Announcer announcer = new ConsoleAnnouncer();
    private Policeman policeman = new PolicemanImpl();


    public void start(Room room) {
        announcer.announce("Начинаем дезинфекцию");
        policeman.makePeopleLeaveRoom();
        desinfector(room);
        announcer.announce("Можете зайти обратно");
    }

    private void desinfector(Room room) {
        System.out.println("Дезинфецируем");
    }
}
