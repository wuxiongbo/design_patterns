package my_demo.monitor.callback.demo2.upper;

import my_demo.monitor.callback.demo2.lower.Director0;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Actress0 {

    private Director0 server = new Director0();

    public void call() {
        server.copy();
    }

    public void ask(){
        System.out.print("询问进度：");

        int x2 = 0;
        while (true) {

            // 主动 询问 导演
            int x1 = this.server.getX(); // 10

            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x2 >= 100) {
                System.out.println();
                break;
            }

            // 打印新进度
            if (x2 != x1) {
                System.out.print(x1 + "% ");
                x2 = x1;
            }

        }
    }

    public static void test() throws InterruptedException {
        Actress0 upper = new Actress0();

        new Thread(() -> upper.call()).start();

        // 主动询问进度
        upper.ask();

        System.out.println("do something...");
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
