package my_demo.monitor.observe.observe.impl;

import my_demo.monitor.observe.observe.IObserver;

/**
 * <p>第一个观察者</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Observer1 implements IObserver {
    @Override
    public void handleNotify(String message) {
        System.out.println("1号观察者接受的消息"+message );
    }
}