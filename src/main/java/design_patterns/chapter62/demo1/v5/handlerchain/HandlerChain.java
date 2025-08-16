package design_patterns.chapter62.demo1.v5.handlerchain;

import design_patterns.chapter62.demo1.v5.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 处理器链 </p>
 *
 * 个人理解：
 * 本对象的职责更像 是 责任链 管理器。 可有可无。  放在 handler抽象类中，使用建造者模式 作为内部类构造器，也是可以的
 *
 * 基于 '数组' 的实现
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

    public void handle(/* 消息上下文 */) {

        // 依次 轮询调用。 不再判断成功与否，均向下传递
        for (IHandler handler : handlers) {

            handler.handle(/* 消息上下文 */);

        }
    }

}
