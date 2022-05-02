package my_demo.monitor.callback.demo5.upper;

import my_demo.monitor.callback.demo5.lower.Director3;
import my_demo.monitor.callback.demo5.upper.Actress3;

import java.util.Observer;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Demo {

    public static void test(){
        Director3 d =new Director3();

        Observer p1 = new Actress3();

        d.addObserver(p1);
        d.copy();
    }

    public static void main(String[] args){

        test();
    }

}
