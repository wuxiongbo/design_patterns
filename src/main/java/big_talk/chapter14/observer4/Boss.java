package big_talk.chapter14.observer4;

import java.util.Observable;
import java.util.Observer;

// //jdk中Observer代码
// public interface Observer {
//     void update(Observable o, Object arg);
// }

// //jdk中Observable代码
// public class Observable {
//     private boolean changed = false;
//     private Vector obs;
//     public Observable() {
//         obs = new Vector();
//     }
//     public synchronized void addObserver(Observer o) {
//         if (o == null)
//             throw new NullPointerException();
//         if (!obs.contains(o)) {
//             obs.addElement(o);
//         }
//     }
//     public synchronized void deleteObserver(Observer o) {
//         obs.removeElement(o);
//     }
//     public void notifyObservers() {
//         notifyObservers(null);
//     }
//     public void notifyObservers(Object arg) {
//         Object[] arrLocal;

//         synchronized (this) {
//             if (!changed)
//                 return;
//             arrLocal = obs.toArray();
//             clearChanged();
//         }

//         for (int i = arrLocal.length-1; i>=0; i--)
//             ((Observer)arrLocal[i]).update(this, arg);
//     }
//     public synchronized void deleteObservers() {
//         obs.removeAllElements();
//     }
//     protected synchronized void setChanged() {
//         changed = true;
//     }
//     protected synchronized void clearChanged() {
//         changed = false;
//     }
//     public synchronized boolean hasChanged() {
//         return changed;
//     }
//     public synchronized int countObservers() {
//         return obs.size();
//     }
// }

class Boss extends Observable{
    protected String name;
    private String action;
    
    public Boss(String name){
        this.name = name;
    }

    //得到状态
    public String getAction(){
        return this.action;
    }
    //设置状态（就是设置具体通知的话）
    public void setAction(String value){
        this.action = value;

        super.setChanged();     //改变通知者的状态

        super.notifyObservers();//调用父类Observable方法，通知所有观察者
    }
}


//看股票同事类

