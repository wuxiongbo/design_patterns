package my_demo.monitor.callback.demo4.upper;

import my_demo.monitor.callback.demo4.lower.Director2;
import my_demo.monitor.callback.demo4.lower.IPerformer;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Demo {

    public static void call(){

        Director2 d =new Director2();

        IPerformer p1 = new Actress();
        IPerformer p2= (i)-> System.out.println("p2收到:"+i+"%");

        d.register(p1);
        d.register(p2);

        d.copy();  //这里由上层模块，触发事件的发生
    }

    public static void main(String[] args){
      call();
    }

}
