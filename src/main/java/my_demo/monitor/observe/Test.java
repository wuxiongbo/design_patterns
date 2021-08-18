package my_demo.monitor.observe;

import my_demo.monitor.observe.observable.Observable;
import my_demo.monitor.observe.observe.IObserve;
import my_demo.monitor.observe.observe.Observe1;
import my_demo.monitor.observe.observe.Observe2;

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

        //创建被观察者
        Observable observable =new Observable();

        //创建多个观察者
        IObserve iObserve1 = new Observe1();
        IObserve iObserve2 = new Observe2();
        //为被观察者添加观察者
        observable.addObserve(iObserve1);
        observable.addObserve(iObserve2);


        //被观察者 ---向---->  观察者   发送信息
        observable.notifyObservers("GOGOGOGOGOGO");

        System.out.println("====删除观察者1=====");
        //删除观察者1
        observable.removeObserve(iObserve1);
        //再次发消息
        observable.notifyObservers("GO");
    }

}
