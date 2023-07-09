package org.arguvos;

@Deprecated
@Singleton
public class RecommendatorImpl implements Recommendator {
    @InjectProperty("wisky")
    private String drink;

    public RecommendatorImpl() {
        System.out.println("Вызван конструктор класса RecomendatorImpl");
    }

    @Override
    public void recommend() {
        System.out.println("Реклама: для защиты пейте " + drink);
    }
}
