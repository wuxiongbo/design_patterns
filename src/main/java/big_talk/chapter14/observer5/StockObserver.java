package big_talk.chapter14.observer5;

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

class StockObserver implements Observer{
    protected String name;
    public StockObserver(String name){
        this.name = name;
    }

    public void update(Observable o, Object arg){
        
        Subject b=(Subject)o;

        System.out.println(b.name+"："+b.getAction()+"！"+this.name+"，请关闭股票行情，赶紧工作。");
    }
}


//看NBA同事类

