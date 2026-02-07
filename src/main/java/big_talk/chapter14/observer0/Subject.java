package big_talk.chapter14.observer0;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public abstract class Subject{
    private ArrayList<Observer> list = new ArrayList<Observer>();//针对抽象的Observer编程
    
    //增加观察者
    public void attach(Observer observer){
        list.add(observer);
    }
    //减少观察者
    public void detach(Observer observer){
        list.remove(observer);
    }
    //通知观察者
    public void notifyObserver(){
        for(Observer item : list){
            item.update();
        }
    }
    @Getter
    @Setter
    protected String subjectState;
}

//具体通知者
