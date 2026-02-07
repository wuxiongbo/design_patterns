package big_talk.chapter14.observer2;

public abstract class Observer{
    protected String name;
    protected Secretary sub;
    public Observer(String name,Secretary sub){
        this.name = name;
        this.sub = sub;
    }
    public abstract void update();
}

//看股票同事类

