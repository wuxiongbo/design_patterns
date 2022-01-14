package chapter62.demo1.v1.handler.concrete;

import chapter62.demo1.v1.handler.Handler;

/**
 * <p> 具体的 处理器类 </p>
 *
 * 存在问题：
 * 实际上，下面的代码实现不够优雅。
 * 处理器类的 handle() 函数，
 *      不仅包含  自己的业务逻辑，
 *      还包含   对下一个处理器的调用，也就是代码中的 successor.handle()。
 * 一个不熟悉这种代码结构的程序员，在添加新的处理器类的时候，很有可能忘记在 handle() 函数中调用 successor.handle()，这就会导致代码出现 bug。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerB extends Handler {

    @Override
    public void handle() {

        // 能否处理
        boolean handled = false;

        //...

        // 不能处理，则交给 下一个处理器 处理
        if (!handled && successor != null) {
            successor.handle();
        }

    }
}