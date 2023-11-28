package my_demo.handlerchain.v3.handler;

import lombok.Getter;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>  处理器抽象类 </p>
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

}
