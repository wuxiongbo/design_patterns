package big_talk.chapter26.flyweight0;

import java.util.Hashtable;

class FlyweightFactory {
    private Hashtable<String,Flyweight> flyweights = new Hashtable<String,Flyweight>();

    public FlyweightFactory(){
        flyweights.put("X", new ConcreteFlyweight());
        flyweights.put("Y", new ConcreteFlyweight());
        flyweights.put("Z", new ConcreteFlyweight());
    }

    public Flyweight getFlyweight(String key) { 
        return (Flyweight)flyweights.get(key);
    }
}

