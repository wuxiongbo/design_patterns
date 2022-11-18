package my_demo.monitor.callback.hollywood.demo4.upper;

import my_demo.monitor.callback.hollywood.demo4.lower.Director2;
import my_demo.monitor.callback.hollywood.demo4.lower.IPerformer;

/**
 * <p>回调 演变成 观察者模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Demo {

    public static void call() {

        // 导演
        Director2 d = new Director2();

        // 演员1
        IPerformer p1 = new Actress();
        // 演员2
        IPerformer p2 = (i) -> System.out.println("p2收到:" + i + "%");

        // 演员关注导演。 有以下两种写法。
//        d.register(p1);
//        d.register(p2);
        p1.register(d);
        p2.register(d);


        d.copy();  //注意： 这里事件的发生 是由 上层模块 触发的。  区别于回调，是由 下层模块 触发
    }

    public static void main(String[] args) {
        call();
    }

}
