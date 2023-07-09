package org.arguvos;

import javax.annotation.PostConstruct;

public class AngryPoliceman implements Policeman {
    @InjectByType
    private Recommendator recommendator;

    @PostConstruct
    public void init() {
        System.out.println("Вызван init метод, recommendator = " + recommendator.getClass());
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("Я сказал, вон все пошли!");
    }
}
