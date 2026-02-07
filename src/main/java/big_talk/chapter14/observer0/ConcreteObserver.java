package big_talk.chapter14.observer0;

public class ConcreteObserver extends Observer{
    private final String name;
    private final Subject sub;
    public ConcreteObserver(String name,Subject sub){
        this.name = name;
        this.sub = sub;
    }
    public void update(){
        System.out.println("观察者"+this.name+"的新状态是"+this.sub.getSubjectState());
    }
}

