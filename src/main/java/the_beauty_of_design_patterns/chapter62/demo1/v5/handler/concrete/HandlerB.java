package the_beauty_of_design_patterns.chapter62.demo1.v5.handler.concrete;

import the_beauty_of_design_patterns.chapter62.demo1.v5.handler.IHandler;

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
    public void handle(/* 消息上下文 */) {

        //...
        System.out.println("HandlerB.handle()");

    }

}