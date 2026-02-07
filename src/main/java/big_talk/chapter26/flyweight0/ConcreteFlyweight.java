package big_talk.chapter26.flyweight0;

import java.util.Hashtable;

class ConcreteFlyweight extends Flyweight {
    public void operation(int extrinsicstate){
        System.out.println("具体Flyweight:"+extrinsicstate);
    }
}

//不需要共享的Flyweight子类

