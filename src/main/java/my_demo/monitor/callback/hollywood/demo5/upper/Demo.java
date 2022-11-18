package my_demo.monitor.callback.hollywood.demo5.upper;

import my_demo.monitor.callback.hollywood.demo5.lower.Director3;
import my_demo.monitor.callback.reactive_programming.FlowDemo;

import java.util.Observer;

/**
 * <p> Observable 已经过时 </p>
 *
 * 新写法，参考{@link FlowDemo}
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Demo {

    public static void test(){
        Director3 d =new Director3();

        Actress3 p1 = new Actress3();

        d.addObserver(p1);
        d.copy();
    }

    public static void main(String[] args){

        test();
    }

}
