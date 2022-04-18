package my_demo.monitor.callback.demo3.upper;

import my_demo.monitor.callback.demo3.lower.Director;
import my_demo.monitor.callback.demo3.lower.IPerformer;

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

        Director director = new Director(this); //传递this ，完成注册

        // 其实，某种角度上来看，这已经是异步了。 因为 update方法，不是 我Actress 调用的。而是 导演Director 调用的。
        // 虽然，我Actress 会因为copy()的调用耗时而等待(或者说阻塞)。但是 update() 这件事不是我主动执行的，
        // 而是 导演Director 通知我执行的。
        // 这就是一种异步思想。即，事情交给别人做，而不是自己做。
        // 所以说，单线程 也可以异步。
        director.copy();

    }


    // 回调方法。
    // 被动接收消息通知
    @Override
    public void update(int i) {
        System.out.println("进度："+ i +"%");
    }


    // 阻塞。
    private static void testBlockingSend(){
        Actress upper =new Actress();
        upper.call();

        System.out.println("do something...");
    }


    // 非阻塞。
    private static void testNonblockingSend() throws InterruptedException{

        new Thread(new Actress()::call).start();
        Thread.sleep(1000);

        System.out.println("do something...");

    }


    /**
     * main 方法写在这里，是为了突显 回调机制。强调，调用由Actress发起，更好理解回调概念。
     *
     * 即，
     * Actress  调  Director
     * 而后
     * Director  回调  Actress
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

//        testBlockingSend();
        testNonblockingSend();

    }
}
