package my_demo.monitor.observe;

import my_demo.monitor.observe.observable.impl.Observable;
import my_demo.monitor.observe.observe.IObserver;
import my_demo.monitor.observe.observe.impl.Observer1;
import my_demo.monitor.observe.observe.impl.Observer2;

/**
 * <p> 观察者模式， 简单实现 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Test {
    public static void main(String[] args) {

        //创建 被观察者
        Observable observable =new Observable();

        //创建 观察者
        IObserver observer1 = new Observer1();
        IObserver observer2 = new Observer2();



        // 向 '被观察者' 注册 '观察者'
        observable.addObserver(observer1);
        observable.addObserver(observer2);



        // '被观察者' 给  '观察者'  发送 通知
        observable.notifyObservers("111111111111");




       /* System.out.println("====删除1号观察者=====");
        //删除观察者1
        observable.removeObserve(iObserve1);

        //再次发消息
        observable.notifyObservers("222222222222");*/
    }

}
