package chapter62.demo1.v3.handlerchain;

import chapter62.demo1.v3.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 处理器链 </p>
 *
 *  基于 '数组'  的实现
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

    // 传递判断放在 职责链
    public void handle() {

        // 依次 轮询调用，
        // 不再交由 处理器传递
        for (IHandler handler : handlers) {

            // 返回处理结果
            boolean handled = handler.handle();


            // 处理成功，则停止轮询
            if (handled) {
                break;
            }

        }
    }

}
