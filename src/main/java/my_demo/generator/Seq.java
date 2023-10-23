package my_demo.generator;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
                Consumer<T> outer2Consumer = new Consumer<T>() {
                    @Override
                    public void accept(T t) {
                        // 3) T -> E ,  Integer-》String
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

    default <E, R> Seq<R> zip(Iterable<E> iterable, BiFunction<T, E, R> mrFunction) {

        // zip 的 Seq 包装了  zipDemo2 的 Seq
        Seq<R> seq = c -> {

            System.out.println("zip  c: " + c);

            Iterator<E> iterator = iterable.iterator();


            // c2 为 zip 的 Consumer
            // zip 的 consumer 包装了  toList 的 consumer
            Consumer<T> c2 = t -> {
                if (iterator.hasNext()) {
                    System.out.println("zip  t: " + t);

                    // c 为 toList() / join() 的 Consumer
                    c.accept(mrFunction.apply(t, iterator.next()));

                } else {
                    stop();
                }
            };


            System.err.println("zip()-consumer_address: " + c2);

            // 调用 zipDemo2 中 Seq 的 consume 方法。
            System.out.println("zip() seq_address: " + Seq.this);
            consumeTillStop(c2);
        };

        System.err.println("zip()-Seq_address: " + seq);

        return seq;

    }

    static <T> T stop() {
        throw StopException.INSTANCE;
    }

    default void consumeTillStop(Consumer<T> consumer) {
        try {
            Seq.this.consume(consumer);
        } catch (StopException ignore) {
        }
    }


    final class StopException extends RuntimeException {
        public static final StopException INSTANCE = new StopException();

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    static String underscoreToCamel(String str) {
        // Java没有首字母大写方法，随便现写一个
        UnaryOperator<String> capitalize = s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();

        // 利用生成器构造一个方法的流
        Seq<UnaryOperator<String>> seq = c -> {

            // yield 第一个小写函数
            c.accept(String::toLowerCase);

            // 这里IDEA会告警，提示死循环风险，无视即可
            while (true) {
                // 按需yield首字母大写函数
                c.accept(capitalize);
            }

        };

        List<String> split = Arrays.asList(str.split("_"));


        return seq.zip(split, Function::apply).join("");
    }

    default String join(String sep) {
        StringJoiner joiner = new StringJoiner(sep);
        Seq.this.consume(t -> joiner.add(t.toString()));
        return joiner.toString();
    }

    /**
     * {@link Seq#zip(Iterable, BiFunction)}
     *
     * @return
     */
    default List<T> toList() {

        List<T> list = new ArrayList<>();

        Consumer<T> consumer = list::add;

        System.err.println("toList()-consumer_address: " + consumer);

        // 调用的是 zip 的 Seq 实现
        System.out.println("toList() seq_address: " + Seq.this);

        Seq.this.consume(consumer);

        return list;
    }


    /**
     * 在Java Stream提供的官方实现里，有一个StreamSupport.stream 的构造工具，可以帮助用户将一个iterator转化为stream。
     * 针对这个入口，我们其实可以用生成器来构造一个非标准的iterator：不实现 hastNext 和 next，而是单独重载 forEachRemaining 方法，
     * 从而 hack 进Stream的底层逻辑 ——
     * 在那迷宫一般的源码里，有一个非常隐秘的角落，一个叫AbstractPipeline.copyInto的方法，
     * 会在真正执行流的时候，调用 Spliterator的 forEachRemaining 方法 来遍历元素——
     * 虽然，这个方法原本是通过 next 和 hasNext实现的，但当我们把它重载之后，就可以做到假狸猫换真太子。
     *
     * @param seq
     * @param <T>
     * @return Stream<T>
     */
    static <T> Stream<T> stream(Seq<T> seq) {
        Iterator<T> iterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                throw new NoSuchElementException();
            }

            @Override
            public T next() {
                throw new NoSuchElementException();
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                seq.consume(action::accept);
            }

        };
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
                false);
    }
}