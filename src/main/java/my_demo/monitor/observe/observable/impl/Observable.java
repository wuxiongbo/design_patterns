package my_demo.monitor.observe.observable.impl;

import my_demo.monitor.observe.observable.IObservable;
import my_demo.monitor.observe.observe.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>被观察者实现类 </p>
 *
 *  维护观察者
 *  通知观察者
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Observable implements IObservable {
    // 由于一个被观察者可以被多个观察者所观察，所以要设置一个观察者链表里
    private final List<IObserver> observers;

    public Observable(){
        //在被观察者对象被创建时，就将集合初始化
        observers = new ArrayList<>();
    }


    // 列表维护
    @Override
    public void addObserver(IObserver observe) {
        observers.add(observe);
    }
    @Override
    public void removeObserver(IObserver observe) {
        observers.remove(observe);
    }


    /**
     * 通知：
     * 可以看到 观察者模式 中，对于 观察者的调度调度都是亲力亲为，自己完成。
     * 这样做的缺点很明显：
     * 1、如果有很多的 直接 和 间接的观察者 的话，将所有的观察者都通知到，会花费很多时间。
     * 2、如果在 观察者 和 被观察者 之间有循环依赖的话，被观察者 会触发它们之间进行循环调用，可能导致系统崩溃。
     * 3、观察者模式 没有相应的机制，让观察者知道所观察的目标对象是怎么发生变化的，
     *   而仅仅只是知道观察目标发生了变化。（即没有确认机制）
     *
     * @param message
     */
    @Override
    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.handleNotify(message);
        }
    }



}
