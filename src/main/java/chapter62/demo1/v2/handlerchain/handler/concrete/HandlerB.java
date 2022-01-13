package chapter62.demo1.v2.handlerchain.handler.concrete;

import chapter62.demo1.v2.handlerchain.handler.Handler;

/**
 * <p> 具体的 处理器类 </p>
 *
 * 抽离 对下一个处理器的调用逻辑 到抽象父类
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerB extends Handler {

    @Override
    protected boolean doHandle() {

        boolean handled = false;

        //...

        return handled;

    }

}