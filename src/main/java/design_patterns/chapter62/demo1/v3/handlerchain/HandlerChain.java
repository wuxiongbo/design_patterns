package design_patterns.chapter62.demo1.v3.handlerchain;

import design_patterns.chapter62.demo1.v3.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 处理器链 </p>
 *
 * 个人理解：
 * 本对象的职责更像 是 责任链 管理器。 可有可无。  放在 handler抽象类中，使用建造者模式 作为内部类构造器，也是可以的
 *
 *  基于 '数组'  的实现
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerChain {

    private final List<IHandler> handlers = new ArrayList<>();

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    // 传递判断放在 职责链
    public void handle(/* 消息上下文 */) {

        // 依次 轮询调用，
        // 不再交由 处理器传递
        for (IHandler handler : handlers) {

            // 返回处理结果
            boolean handled = handler.handle(/* 消息上下文 */);


            // 处理成功，则停止轮询
            if (handled) {
                break;
            }

        }
    }

}
