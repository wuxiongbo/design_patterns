package my_demo.handlerchain.v3.handlerchain;

import my_demo.handlerchain.v3.handler.FunctionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
public class HandlerChain<T> {

    private final List<FunctionHandler<T>> handlers = new ArrayList<>();

    public HandlerChain<T> addHandler(FunctionHandler<T> handler) {
        this.handlers.add(handler);
        return this;
    }

    public T handle(T t) {

        Function<T,T> processor = Function.identity();

        // 依次 轮询调用
        // 不再交由 处理器传递
        for (FunctionHandler<T> handler : handlers) {
            if(handler.test(t)){
                processor = processor.andThen(handler.getProcessor());
            }
        }

        return processor.apply(t);
    }

}
