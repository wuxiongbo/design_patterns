package chapter62.demo1.v3.handlerchain;

import chapter62.demo1.v3.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 处理器链 </p>
 *
 * 单向连表
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerChain {

    private List<IHandler> handlers = new ArrayList<>();

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public void handle() {

        // 依次调用
        // 轮询调用，而不是交由 处理器传递
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) {
                break;
            }
        }
    }

}
