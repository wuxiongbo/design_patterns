package my_demo.generator;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 柯里化的应用
 * <p>
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
            /**
             * 这里把 outer1Consumer 当做 System.out::println  ，方便理解
             * 外部最先调用的是多层嵌套Seq类中 最后一层Seq类的 consume 方法。
             * 最后一层Seq 的 consume 方法实现逻辑 又懒加载， 层层传递，最终实际最先执行的逻辑是 最内层Seq类的 consume 方法
             * @param outer1Consumer
             */
            @Override
            public void consume(Consumer<E> outer1Consumer) {

                // 1) 内部类的 Consumer 消费者， 将 对 从T类型转型为的E类型元素  的消费行为，进行包装。 这部分逻辑实现了懒加载
                Consumer<T> outer2Consumer = new Consumer<T>() {
                    /**
                     * 将 Function 函数 闭包进了 Consumer 的实现当中。 层层闭包，就实现了函数柯里化。
                     * @param t the input argument
                     */
                    @Override
                    public void accept(T t) {
                        // 3) T -> E ,  Integer-》String
                        outer1Consumer.accept(function0.apply(t));
                    }
                };


                // 2) 内部类的 consume 调用  外部类的 consume 方法。
                // （从代码书写直观的看，是 代码结构内层的类 传递到了 代码结构外层的类）
                // （实际上，是从逻辑层面，也就是从包装层级看，就是多层嵌套Seq类中，是外层Seq类将 consume方法的内部实现 传递给了内层 Seq类 的  consume方法）
                Seq.this.consume(outer2Consumer);

            }

        };

        //  return c -> consume( t -> c.accept(function.apply(t)) );

    }

    // 如果觉得理解起来不太直观，就把Seq看作是List，把consume看作是forEach就好。
    //                                       甚至，还可以 把 consumer 看做是 System.out::println
    default <E> Seq<E> map1(Function<T, E> function) {
        return consumer -> Seq.this.consume(t -> consumer.accept(function.apply(t)));
    }

    static <T> Seq<T> unit(T t) {
        return c -> c.accept(t);
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

    // 如果觉得理解起来不太直观，就把Seq看作是List，把 consume 看作是forEach就好。
    //                                       甚至还可以 把 consumer 看做是 System.out::println
    default Seq<T> filter(Predicate<T> predicate) {
        return consumer -> consume(t -> {
            if (predicate.test(t)) {
                consumer.accept(t);
            }
        });
    }


    default Seq<T> take(int n) {
        return consumer -> {
            AtomicInteger i = new AtomicInteger(n);
            consumeTillStop(t -> {
                if (i.getAndDecrement() > 0) {
                    consumer.accept(t);
                } else {
                    stop();
                }
            });
        };
    }


    default Seq<T> take1(int n) {
        return consumer -> {
            AtomicInteger i = new AtomicInteger(n);

            consume(t -> {
                if (i.getAndDecrement() > 0) {
                    consumer.accept(t);
                } else {
                    stop();
                }
            });

        };
    }


    default <E, R> Seq<R> zip(Iterable<E> iterable, BiFunction<T, E, R> mrFunction) {

        // zip 的 Seq 包装了  zipDemo2 的 Seq。  内层 zip 的 Seq 的 consume 方法 闭包了 外层 zipDemo2 的 Seq 的 consume 方法
        Seq<R> seq = c -> {

            // c 为 toList/join 传递进来的 Consumer
            System.out.println("zip() 里面接收的 c: " + c);

            Iterator<E> iterator = iterable.iterator();


            // consumer 被循环执行。
            // zip 的 consumer 包装了  toList 的 consumer
            Consumer<T> consumer = t -> {
                if (iterator.hasNext()) {

                    System.out.println("zip() 里面接收的 t 的地址值: " + t);

                    System.out.println("zip()入参中的 mrFunction 的地址值: " + mrFunction);

                    // c 为 toList/join 传递进来的 Consumer， 在 zipDemo2 中，t 是个 function
                    c.accept(mrFunction.apply(t, iterator.next()));


                } else {
                    stop();
                }
            };

            System.err.println("zip() 里面定义的 consumer 的地址值: " + consumer);

            // 调用 客户端方法zipDemo2() 中， Seq 的 consume 方法。
            System.out.println("zip() 外层 Seq.this 的地址值: " + Seq.this);
            consumeTillStop(consumer);

        };

        System.err.println("zip() 里面定义的 seq 的地址值: " + seq);

        return seq;

    }

    static void stop() {
        throw StopException.INSTANCE;
    }

    default void consumeTillStop(Consumer<T> consumer) {
        try {
            Seq.this.consume(consumer);
        } catch (StopException ignore) {
        }
    }


    static String underscoreToCamel(String str) {
        // Java没有首字母大写方法，随便现写一个
        UnaryOperator<String> capitalize = s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();

        // 利用生成器构造一个方法的流
        Seq< UnaryOperator<String> > seq = c -> {

            // yield 第一个函数————单词小写函数
            c.accept(String::toLowerCase);

            // 这里IDEA会告警，提示死循环风险，无视即可
            while (true) {
                // yield 第二个函数————首字母大写函数
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
        Consumer<T> c = list::add;

        // 这里将 toList() 的 Consumer 传递给了 zip()
        System.err.println("toList() 里面定义的 consumer 的地址值: " + c);

        // 这里调用的是 zip Seq 的 consume 实现。
        System.out.println("toList() 外层 Seq.this 的地址值: " + Seq.this);
        Seq.this.consume(c);

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
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.ORDERED), false);
    }
}