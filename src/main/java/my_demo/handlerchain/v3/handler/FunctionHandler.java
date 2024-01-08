package my_demo.handlerchain.v3.handler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p> 处理器抽象类 </p>
 * <p>
 * 设计思路: handler 组合了 Function / Predicate 分别代表 处理流程抽象/判断逻辑抽象.
 * 仅用于构建链式结构
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class FunctionHandler<T> {

    @Getter
    private final Function<T, T> processor;

    private final Predicate<T> predicate;

    public FunctionHandler(Predicate<T> predicate, Function<T, T> processor) {
        Objects.requireNonNull(predicate);

        Objects.requireNonNull(processor);
        this.processor = processor;
        this.predicate = predicate;
    }

    /**
     * 是否执行当前处理器. false,跳过当前执行  true,正常执行
     *
     * @param t 上下文
     * @return 是否执行
     */
    public final boolean processCurrent(T t) {
        return predicate.test(t);
    }

    public static <R> HandlerChain<R> chainBuilder() {
        return new HandlerChain<>();
    }


    /**
     * 职责链
     *
     * @param <T> 上下文
     */
    public final static class HandlerChain<T> {

        private final List<FunctionHandler<T>> handlers = new ArrayList<>();

        public HandlerChain<T> addHandler(FunctionHandler<T> handler) {
            this.handlers.add(handler);
            return this;
        }

        public T handle(T t) {
            return handlerChain(t).apply(t);
        }

        /**
         * 构建执行链
         *
         * @param t 上下文
         * @return 职责链
         */
        private Function<T, T> handlerChain(T t) {
            Function<T, T> processor = Function.identity();

            // 依次 轮询调用
            // 不再交由 处理器传递
            for (FunctionHandler<T> handler : handlers) {
                if (handler.processCurrent(t)) {
                    processor = processor.andThen(handler.getProcessor());
                }
            }
            return processor;
        }
    }


}
