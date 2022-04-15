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
        new Director(this).copy();  //传递this ，完成注册
    }


    // 回调方法。
    // 被动接收消息通知
    @Override
    public void update(int i) {
        System.out.println("进度："+ i +"%");
    }


    // 阻塞
    private static void testBlockingSend(){
        Actress upper =new Actress();
        upper.call();

        System.out.println("do something...");
    }


    // 非阻塞
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
