package org.arguvos;

public class RecommendatorImpl implements Recommendator {
    @InjectProperty("wisky")
    private String drink;
    @Override
    public void recommend() {
        System.out.println("Реклама: для защиты пейте " + drink);
    }
}
