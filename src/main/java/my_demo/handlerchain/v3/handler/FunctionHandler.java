package my_demo.handlerchain.v3.handler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p> 处理器抽象类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class FunctionHandler<T> {

    @Getter
    private final Function<T,T> processor;

    private final Predicate<T> predicate;

    public final boolean test(T t) {
        return predicate.test(t);
    }

    public FunctionHandler(Predicate<T> predicate, Function<T,T> processor){
        this.processor = processor;
        this.predicate = predicate;
    }

    public static <R> HandlerChain<R> build() {
        return new HandlerChain<>();
    }


    public static class HandlerChain<T> {

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
}
