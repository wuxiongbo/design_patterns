package big_talk.chapter14.observer2;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public class Secretary{
    protected String name;
    public Secretary(String name){
        this.name = name;
    }
    //同事列表
    private ArrayList<Observer> list = new ArrayList<Observer>();//针对抽象的Observer编程
    @Getter
    @Setter
    private String action;

    //增加同事（有几个同事需要前台通知，就增加几个对象）
    public void attach(Observer observer){
        list.add(observer);
    }
    //减少同事
    public void detach(Observer observer){
        list.remove(observer);
    }
    //通知
    public void notifyEmployee(){
        //待老板来了，就给所有登记过的同事发通知
        for(Observer item : list){
            item.update();
        }
    }
    //得到前台状态
    //设置前台状态（就是设置具体通知的话）
}

//抽象观察者

