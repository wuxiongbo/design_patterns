package big_talk.chapter26.flyweight0;

public class UnsharedConcreteFlyweight extends Flyweight {
    public void operation(int extrinsicstate){
        System.out.println("不共享的具体Flyweight:"+extrinsicstate);
    }
}

//享元工厂

