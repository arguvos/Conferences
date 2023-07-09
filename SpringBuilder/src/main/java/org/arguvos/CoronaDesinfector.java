package org.arguvos;

public class CoronaDesinfector {

    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;


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
