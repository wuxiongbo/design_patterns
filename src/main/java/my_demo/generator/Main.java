package my_demo.generator;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 *  <a href="https://mp.weixin.qq.com/s/v-HMKBWxtz1iakxFL09PDw">参考文档</a>
 *
 */
public class Main {
    public static void main(String[] args) {


        List<Integer> list = Arrays.asList(1, 2, 3);
        // 已绑定this 的方法引用
        Seq<Integer> myStream = list::forEach;


//        list.forEach(System.out::println);
//        seq.consume(System.out::println);


        // 一、 map的实现
        // Integer -> String -> Long -> BigDecimal
        //
        Seq<BigDecimal> mapAndThanForEach = myStream
                // 1）调用完此方法，function 包装进入了一层Seq 内部类
                .map(
                        (t) -> {
                            return t + "---";
                        }
                )
                // 2）在以上 Seq内部类的基础上，再包装一层内部类
                .map(
                        t -> Long.valueOf(t.replace("---", ""))
                )
                .map(
                        BigDecimal::new
                );

        // 二、消费元素(实际调用者，是被层层包装过的匿名类)
        // 1） 遍历 元素
        // 2） map: integer -》String
        // 3） consumer: 打印 String
        mapAndThanForEach.consume(System.out::println);


        System.out.println("--------------------------");

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<List<Integer>> lists = List.of(list1, list2);

        Seq<List<Integer>> stream1 = lists::forEach;
        Seq<Integer> integerSeq = stream1.flatMap(Main::seq);

        integerSeq.consume(System.out::println);

    }

    @Test
    void nestConsumerTest() {
        // Integer -> String
        Function<Integer, String>  function3 = t -> {
            System.out.println("Integer -> String");
            return t + "===111";
        };
        // String -> Long
        Function<String, Long>     function2 = e -> {
            System.out.println("String -> Long");
            return Long.valueOf(e.replace("===111", ""));
        };
        // Long   -> BigDecimal
        Function<Long, BigDecimal> function1 = v -> {
            System.out.println("Long -> BigDecimal");
            return new BigDecimal(v);
        };

        nestConsumer1(function3, function2, function1, 111);

        System.out.println("=====================");

        nestConsumer1(function3, function2, function1, 222);
    }

    <T, E, V, F> void nestConsumer1(
            Function<T, E> function1,
            Function<E, V> function2,
            Function<V, F> function3, T t) {

        Consumer<F> innerConsumer = System.out::println;

        // function3   V->F
        Consumer<V> outter1Consumer = new Consumer<V>() {
            @Override
            public void accept(V v) {
                innerConsumer.accept(function3.apply(v));
            }
        };


        // function2   E->V
        Consumer<E> outter2Consumer = new Consumer<E>() {
            @Override
            public void accept(E e) {
                outter1Consumer.accept(function2.apply(e));
            }
        };


        // function1   T->E
        Consumer<T> outter3Consumer = new Consumer<T>() {
            @Override
            public void accept(T t) {
                outter2Consumer.accept(function1.apply(t));
            }
        };


        outter3Consumer.accept(t);

    }


    <T, E, V, F> void nestConsumer2(
            Function<T, E> function3,
            Function<E, V> function2,
            Function<V, F> function1, T t) {

        Consumer<F> innerConsumer = System.out::println;

        // function1   T->E
        Consumer<T> outter3Consumer =
                new Consumer<T>() {
                    @Override
                    public void accept(T t) {
                        new Consumer<E>() {
                            @Override
                            public void accept(E e) {
                                new Consumer<V>() {
                                    @Override
                                    public void accept(V v) {
                                        innerConsumer.accept(function1.apply(v));
                                    }
                                }.accept(function2.apply(e));
                            }
                        }.accept(function3.apply(t));
                    }
                };


        outter3Consumer.accept(t);

    }


    void testset() {

    }


    static <T> Seq<T> unit(T t) {
        return c -> c.accept(t);
    }


    static Seq<Integer> seq(List<Integer> list) {
        return list::forEach;
    }

}
