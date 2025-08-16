package design_patterns.chapter62.demo1.v3.handler.concrete;

import design_patterns.chapter62.demo1.v3.handler.IHandler;

/**
 * <p> 具体的 处理器类 </p>
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerB implements IHandler {
    @Override
    public boolean handle(/* 消息上下文 */) {

        boolean handled = false;

        //...业务逻辑

        return handled;
    }
}