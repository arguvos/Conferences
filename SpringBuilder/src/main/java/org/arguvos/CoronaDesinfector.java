package org.arguvos;

public class CoronaDesinfector {

    private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    private Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);


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
