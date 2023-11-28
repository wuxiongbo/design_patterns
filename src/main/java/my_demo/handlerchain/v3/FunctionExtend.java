package my_demo.handlerchain.v3;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Xander Wu
 * @date 2023/11/15 13:35
 */
public abstract class FunctionExtend<T,C> implements Function<T, T>, Predicate<C> {

    private C c;

    public FunctionExtend<T,C> test1(C c){
        this.c = c;
        return this;
    }

    public Function<T, T> andThenWithCondition(Function<T, T> after) {
        if(test(c)){
            return Function.super.andThen(after);
        } else {
            return this;
        }

    }

}
