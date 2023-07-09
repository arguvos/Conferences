package org.arguvos;

public class AngryPoliceman implements Policeman {
    @InjectByType
    private Recommendator recommendator;
    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("Я сказал, вон все пошли!");
    }
}
