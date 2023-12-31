package my_demo.monitor.callback.demo1;

/**
 * <p> 回调对象(被调用方) </p>
 * 类似 观察者 的角色
 * <p>
 * 本示例的目的是 理解 “回调” 的概念
 * 三种写法：
 * 1）本 回调对象 定义 匿名类 包裹回调方法
 * 2）本 回调对象 使用 lambda表达式，直接传入回调方法的实现
 * 3）本 回调对象 自己实现 回调方法
 * <p>
 * 广义理解，包含了 回调方法实现的对象，都可以称为回调对象。所以，回调非常的灵活多变
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Callee
        implements ICallBack {  // 第三种写法。在本类中 定义 回调函数的实现。


    /**
     * 第一种写法。在本类中 使用lambda
     * 回调函数:
     * Callee.$匿名内部类.callBack();
     * 被回调对象:
     * Callee自身
     */
    public void process1() {
        // 回调者
        Caller caller = new Caller();

        caller.call(() -> System.out.println("回调对象" + this + "的lambda回调函数调用成功!"));

    }

    /**
     * 第二种写法。在本类中 定义 回调函数实现。
     * 回调函数:
     *     Callee.callBack();
     * 回调对象:
     *     Callee自身
     */
    public void process2() {
        // 回调者
        Caller caller = new Caller();
        caller.call(this);
    }

    @Override
    public void callBack() {
        System.out.println("回调对象" + this + "的回调函数回调成功!");
    }

    /**
     * 第三种写法。在本类中 定义 匿名内部类
     * 回调函数:
     * Callee.$匿名内部类.callBack();
     * 被回调对象:
     * Callee.$匿名内部类
     */
    public void process3() {
        // 回调者
        Caller caller = new Caller();

        caller.call(new ICallBack() {
            @Override
            public void callBack() {
                System.out.println("回调对象" + this + "的回调函数回调成功!");
            }
        });
    }


    public static void main(String[] args) {
        Callee callee = new Callee();
        callee.process1();
        callee.process2();
        callee.process3();
    }


/*
输出结果：

Start...
回调对象my_demo.monitor.callback.demo1.Callee@7823a2f9的lambda回调函数调用成功!
End...
Start...
回调对象my_demo.monitor.callback.demo1.Callee@7823a2f9的回调函数回调成功!
End...
Start...
回调对象my_demo.monitor.callback.demo1.Callee$1@2b98378d的回调函数回调成功!
End...


 */

}
