package big_talk.chapter14.observer3;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public abstract class Subject{
    protected String name;
    public Subject(String name){
        this.name = name;
    }
    //同事列表
    private ArrayList<Observer> list = new ArrayList<Observer>();//针对抽象的Observer编程
    @Getter
    @Setter
    private String action;
    //增加同事（有几个同事需要秘书通知，就增加几个对象）
    public void attach(Observer observer){
        list.add(observer);
    }
    //减少同事
    public void detach(Observer observer){
        list.remove(observer);
    }
    //通知
    public void notifyEmployee(){
        //给所有登记过的同事发通知
        for(Observer item : list){
            item.update();
        }
    }
    //得到状态
    //设置状态（就是设置具体通知的话）
}

//老板

