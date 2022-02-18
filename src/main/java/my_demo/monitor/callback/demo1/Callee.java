package my_demo.monitor.callback.demo1;

/**
 * <p> 回调对象 </p>
 * 本示例的目的是 理解 “回调” 的概念
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Callee
        // 第三种写法。在本类中 定义 回调函数实现。
        implements ICallBack{


    public void process(){

        // 回调者
        Caller caller = new Caller();


        // 第一种写法。在本类中 使用lambda
        // 回调函数:
        //     Callee.匿名类.callBack();
        // 回调对象:
        //     Callee自身
        caller.call(() -> System.out.println("回调对象"+this+"的回调函数调用成功!"));



        // 第二种写法。在本类中 定义 匿名内部类
        // 回调函数:
        //     Callee.匿名类.callBack();
        // 回调对象:
        //     Callee.匿名类
        caller.call(new ICallBack(){
            @Override
            public void callBack() {
                System.out.println("回调对象"+this+"的回调函数回调成功!");
            }
        });


        // 第三种写法。在本类中 定义 回调函数实现。
        // 回调函数:
        //     Callee.callBack();
        // 回调对象:
        //     Callee自身
        caller.call(this);
    }


    // 第三种写法。在本类中 定义 回调函数实现。
    @Override
    public void callBack() {
        System.out.println("回调对象"+this+"的回调函数回调成功!");
    }




    public static void main(String[] args) {
        Callee callee = new Callee();
        callee.process();
    }


/*
输出结果：

Start...
回调对象my_demo.monitor.callback.demo1.Callee@41906a77的回调函数调用成功!
End...
Start...
回调对象my_demo.monitor.callback.demo1.Callee$1@4b9af9a9的回调函数回调成功!
End...
Start...
回调对象my_demo.monitor.callback.demo1.Callee@41906a77的回调函数回调成功!
End...


 */

}
