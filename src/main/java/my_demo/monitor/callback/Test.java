package my_demo.monitor.callback;

import my_demo.monitor.callback.callback.Caller;
import my_demo.monitor.callback.callback.ICallBack;

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
        Caller call = new Caller();





        // 第一种写法。匿名内部类
        call.call(() -> System.out.println("回调函数调用成功!"));




        // 第二种写法。匿名内部类
        ICallBack callBack = () -> System.out.println("回调函数回调成功!");
        call.call(callBack);




        // 第三种写法。定义类，然后将类实例化 实现这个ICallBack接口类
        class CallBackImpl implements ICallBack {
            @Override
            public void callBack() {
                System.out.println("回调函数回调成功!");
            }
        }
        call.call(new CallBackImpl());


    }

}
