package my_demo.generator;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 柯里化的应用
 * 两层 Consumer 嵌套，外层 Consumer 依赖内层 Consumer
 *
 * @param <T>
 */
public interface Seq<T> {

    // 迭代器的引用
    void consume(Consumer<T> consumer);


    default <E> Seq<E> map(Function<T, E> function0) {
        // 闭包
        // Seq 匿名内部类
        return new Seq<E>() {
            // 这里把 outer1Consumer 当做 System.out::println  ，方便理解
            // （外部最先调用的是多层嵌套类中最内层类的 consume 方法。）
            @Override
            public void consume(Consumer<E> outer1Consumer) {

                // 1) 内部类的 Consumer 消费者， 将 对 从T类型转型为的E类型元素  的消费行为，进行包装。
                Consumer<T> outer2Consumer= new Consumer<T>(){
                    @Override
                    public void accept(T t){
                        // 3) T -> E ,  Integer-》Strin                  g
                        outer1Consumer.accept(function0.apply(t));
                    }
                };


                // 2) 内部类的 consume 调用  外部类的 consume 方法。
                Seq.this.consume(outer2Consumer);

            }

        };

        //  return c -> consume( t -> c.accept(function.apply(t)) );

    }

    default <E> Seq<E> flatMap(Function<T, Seq<E>> function) {
//        return c -> consume(t -> function.apply(t).consume(c));

        return new Seq<E>() {
            @Override
            public void consume(Consumer<E> consumer) {

                // 1） Seq<T> 遍历元素 T  integer
                Seq.this.consume(
                        // 2) 消费元素 T：
                        (t) -> {
                            // 3）映射转换：T -> Seq<E>
                            function.apply(t)
                                    // 4）遍历：Seq<E> 遍历元素 E
                                    .consume(consumer);
                        }
                );

            }

        };

    }

    default Seq<T> filter(Predicate<T> predicate) {
        return c -> consume(t -> {
            if (predicate.test(t)) {
                c.accept(t);
            }
        });
    }
}