package big_talk.chapter14.observer0;

import java.util.ArrayList;

abstract class Subject{
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
    protected String subjectState;
    public String getSubjectState(){
        return this.subjectState;
    }
    public void setSubjectState(String value){
        this.subjectState = value;
    }
}

//具体通知者

