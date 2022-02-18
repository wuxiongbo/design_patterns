package my_demo.monitor.callback.demo1;

import my_demo.monitor.callback.demo1.callee.CallBack;
import my_demo.monitor.callback.demo1.caller.Caller;
import my_demo.monitor.callback.demo1.callee.ICallBack;

/**
 * <p> 回调 </p>
 * 本示例的目的是 理解 “回调” 的概念
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Test {

    public static void main(String args[]) {
        // 回调者
        Caller caller = new Caller();




        // 第一种写法。lambda
        caller.call(() -> System.out.println("回调函数调用成功!"));



        // 第二种写法。匿名内部类
        caller.call(new ICallBack(){
            @Override
            public void callBack() {
                System.out.println("回调函数回调成功!");
            }
        });


        // 第三种写法。定义 回调函数的实现类
        caller.call(new CallBack());

    }

}
