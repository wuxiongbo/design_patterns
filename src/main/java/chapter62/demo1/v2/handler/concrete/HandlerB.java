package chapter62.demo1.v2.handler.concrete;

import chapter62.demo1.v2.handler.Handler;

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

    // 子类只需要实现 算法骨架
    @Override
    protected boolean doHandle(/* 消息上下文 */) {

        boolean handled = false;

        //...省略一堆业务处理...


        // 抽离 请求的传递逻辑。
//        if (!handled && successor != null) {
//            successor.handle();
//        }


        return handled;

    }

}