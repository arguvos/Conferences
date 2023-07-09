package org.arguvos;

@Singleton
public class RecommendatorImpl implements Recommendator {
    @InjectProperty("wisky")
    private String drink;

    public RecommendatorImpl() {
        System.out.println("Recomendator was created");
    }

    @Override
    public void recommend() {
        System.out.println("Реклама: для защиты пейте " + drink);
    }
}
