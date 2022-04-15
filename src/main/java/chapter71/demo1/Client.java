package chapter71.demo1;

import chapter71.demo1.Invoker.GameApplication;

/**
 * <p> 命令模式、指令模式 </p>
 *
 * 命令模式用的 “最核心的实现手段”是， 将‘函数’封装成‘对象’。
 *
 * 我们知道，C 语言支持函数指针，我们可以把 函数 当作变量传递来传递去。
 * 但是，在大部分编程语言中，函数 没法儿作为 参数 传递给其他函数，也没法儿赋值给变量。
 *
 * 借助 命令模式，我们可以将函数封装成对象：
 *    具体来说就是，
 *    设计一个包含这个函数的类，实例化一个对象，传来传去，
 *    这样，就可以实现把函数像对象一样使用。
 *
 *    从实现的角度来说，它类似我们之前讲过的 ‘回调’。
 *    见：{@link chapter59.callback}
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Client {

    public static void main(String[] args) {

        // 构造 Invoker
        GameApplication invoker = new GameApplication();
        // 调用命令对象
        invoker.mainloop();

    }

}
