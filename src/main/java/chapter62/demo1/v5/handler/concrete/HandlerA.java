package chapter62.demo1.v5.handler.concrete;

import chapter62.demo1.v5.handler.IHandler;

/**
 * <p> 具体的 处理器类 </p>
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerA implements IHandler {

    @Override
    public void handle(/* 消息上下文 */) {

        //...
        System.out.println("HandlerA.handle()");

    }

}