package my_demo.monitor.callback.hollywood.demo3.upper;

import my_demo.monitor.callback.hollywood.demo3.lower.Director;
import my_demo.monitor.callback.hollywood.demo3.lower.IPerformer;

/**
 * <p>女演员</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Actress implements IPerformer {

    public void call() {

        //传递this ，完成注册
        // 上文介绍过，回调有三种写法：
        //    1）本 回调对象 定义 匿名类 包裹回调方法
        //    2）本 回调对象 使用 lambda表达式，直接传入回调方法的实现
        //    3）本 回调对象 自己实现 回调方法。
        // 这里，使用第三种写法
        Director director = new Director(this);


        // 如果是从 testBlockingSend() 函数进来，其实，某种角度上来看，这已经是异步了。
        // 因为 update方法，不是 我Actress 调用的。而是 由导演Director 调用的。
        // 虽然，我Actress 也会因为copy()的调用耗时而等待(或者说阻塞)。
        // 但是，update() 这件事不是我主动执行的，而是 导演Director 通知我执行的。
        // 这就是一种异步思想。即，事情交给别人做，而不是自己做。  调用方 不是主动获取结果，而是被动 通知到结果。
        // 所以说，单线程 也可以 '异步'。 对于 异步 的理解，关键就是在这里。
        director.copy();


    }


    // 回调方法。
    // 被动接收消息通知
    @Override
    public void update(int i) {
        System.out.println("进度："+ i +"%");
    }


    // 异步 阻塞。
    private static void testBlockingSend(){

        Actress upper =new Actress();

        // 打电话给导演，我要演戏。
        upper.call();


        // 进度通知。
        // 导演进度未结束，我没心情做其他事。 等在这里吧。

        // 通知结束了，总算安心可以做其他事了
        System.out.println("do something...");

    }


    // 异步 非阻塞。
    private static void testNonblockingSend() throws InterruptedException{

        Actress upper =new Actress();

        // 打电话给导演，我要演戏。
        new Thread(upper::call).start();
        Thread.sleep(1000);


        // 不管结果如何，我先做做别的事
        System.out.println("do something...");

    }


    /**
     * main 方法写在这里，是为了突显 回调机制。强调，调用由Actress发起，更好理解回调概念。
     *
     * 即，
     * Actress 向 Director 注册 update方法
     * Actress 调 Director
     * 而后，未来某时刻
     * Director 回调 Actress  注册的 update方法
     *
     * 注意： 这里，事件的发生  是由 下层模块 触发的。   区别于观察者模式，是由 上层模块 触发
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

//        testBlockingSend();
        testNonblockingSend();

    }
}
