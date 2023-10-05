package the_beauty_of_design_patterns.chapter62.demo1.v3.handler.concrete;

import the_beauty_of_design_patterns.chapter62.demo1.v3.handler.IHandler;

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
    public boolean handle(/* 消息上下文 */) {

        boolean handled = false;

        //...业务逻辑

        return handled;

    }

}