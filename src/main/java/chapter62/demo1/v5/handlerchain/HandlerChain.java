package chapter62.demo1.v5.handlerchain;

import chapter62.demo1.v5.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 处理器链 </p>
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

    public void handle() {

        // 依次 轮询调用。 不再判断成功与否，均向下传递
        for (IHandler handler : handlers) {

            handler.handle();

        }
    }

}
