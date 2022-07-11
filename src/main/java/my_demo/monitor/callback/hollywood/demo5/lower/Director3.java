package my_demo.monitor.callback.hollywood.demo5.lower;

import java.util.Observable;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Director3 extends Observable {

    public void copy() {

        for(int i=0;i<100;i++){

            // 在适当的时机调用回调 ，时机由我定
            try{
                Thread.sleep((long)(100*Math.random()));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i%10 == 0) {
                setChanged();

                notifyObservers(i);// 通知观察者

            }
        }

        System.out.println("copy() over");
    }
}