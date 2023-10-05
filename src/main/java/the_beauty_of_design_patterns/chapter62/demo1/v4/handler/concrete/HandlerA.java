package the_beauty_of_design_patterns.chapter62.demo1.v4.handler.concrete;

import the_beauty_of_design_patterns.chapter62.demo1.v4.handler.Handler;

/**
 * <p> 具体的 处理器类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerA extends Handler {

    @Override
    protected void doHandle(/* 消息上下文 */) {

        //...
        System.out.println("HandlerA.handle()");

    }

}