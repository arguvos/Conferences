# Spring Потрошитель Part 1
## Обобщенная схема построения контекста:
![Текст описания](img/Untitled.png)

## Действующие лица:

### Bean Definition Reader
- Считывает из xml файла все декларации бинов, и кладет их в словарь BeanDefinitions:
  - Id - идентификатор бина
  - BeanDefenition - описание бина

### Bean Factory
- На основе мапы BeanDefinitions создает из классов объекты и все бины складывает в контейнер IoC Container.
- В зависимости от scope бин может создаваться в разное время:
  - Singleton - создается в момент, когда поднимается контекст по умолчанию и складывается в контейнер.
  - Prototype - создается только в тот момент, когда он нужен и в контейнере не хранится, поэтому destroy-методы для prototype никогда не работают.
- В первую очередь создаются бины, которые имплементируют интерфейс BeanPostProcessor и потом с их помощью настраивает остальные бины.
- BeanFactory проходится по BeanPostProcessor-ам дважды:
  - До init метода.
  - После init метода.

### Bean Post Processor
- Позволяет настраивать бины до того, как они попали в IoC Container.
- Задействует паттерн "Цепочка обязанностей" (Chain of responsibility)
- Интерфейс содержит два метода. В каждый метод приходит объект bean и его имя.
  - Object postProcessBeforeInitialization(Object bean, String beanName) - вызывается до init метода. Используется для модификации бина.
  - Object postProcessAfterInitialization(Object bean, String beanName) - вызывается после init метода. Используется для создания прокси.

## Создание аннотаций:
- Для создания собственных аннотаций используется ключевое слово @interface. При описании аннотации используются следующие аннотации: 
  -  @Target - к чему применяется аннотация: METHOD, CONSTRUCTOR, etc.
  -  @Repeatable - повторяющаяся аннотация.
  -  @Retention - определяет доступность в течении жизненного цикла программы:
     - RetentionPolicy.SOURCE - аннотация останется только в файлах исходников, в .class-файлы сведения о ней не попадут.
     - RetentionPolicy.CLASS - (значение по умолчанию) аннотация будет сохранена в .class-файлах, но не будет доступна во время выполнения программы.
     - RetentionPolicy.RUNTIME - аннотация будет сохранена в .class-файлах, доступна во время выполнения программы.
- Для имплементации логики аннотации используется класс реализующий интерфейс BeanPostProcessor.

## Трехфазовый конструктор:
1. Java constructor - связи spring отсутствуют
2. @PostConstruct - зависимости внедрены, но прокси не настроены. Метод всегда работает на оригинальный объект, до того как все прокси накрутились на него.
3. @PostProxy - отрабатывает когда все зависимости построены и настроены. Под капотом использует ApplicationListener. Данный интерфейс слушает Spring Context и отслеживает все события которые с ним происходят. Например:
   - SpringStartedEvent - Event raised when an ApplicationContext gets started 
   - SpringStoppedEvent - Event raised when an ApplicationContext gets stopped.
   - ContextRefreshedEvent - Event raised when an ApplicationContext gets initialized or refreshed.

```java
@Profiling
public class TerminatorQuoter implements Quoter {
    @InjectRandomInit(min = 2, max = 7)
    private int repeat;
    private String message;

    public TerminatorQuoter() {
        System.out.println("Phase 1");          //Первая фаза
    }

    @PostConstruct
    public void init() {
        System.out.println("Phase 2");          //Вторая фаза
        System.out.println(repeat);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3");          //Третья фаза
        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }
}
```