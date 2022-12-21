package my_demo.monitor.observe_jdk;

import java.util.Observable;

/**
 * 被观察的对象 ObserverDemo
 * @author Xander Wu
 * @date 2022/12/20 10:09
 */
public class ObserverDemo extends Observable {




    public static void main(String[] args) {
        ObserverDemo observerDemo = new ObserverDemo();

        //添加观察者
        observerDemo.addObserver((o,arg)->{
            System.out.println("数据发生变化A");
        });
        observerDemo.addObserver((o,arg)->{
            System.out.println("数据发生变化B");
        });


        //将此Observable对象标记为已更改
        observerDemo.setChanged();


        //如果该对象发生了变化，则通知其所有观察者
        observerDemo.notifyObservers();


    }
}
