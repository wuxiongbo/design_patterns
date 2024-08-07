package my_demo.generator;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 生成器设计模式
 * <p>
 * <a href="https://mp.weixin.qq.com/s/v-HMKBWxtz1iakxFL09PDw">参考文档</a>
 * @author bear
 */
public class SequenceTest {

    @Test
    public void seqTest() {
        // 1）生产数据
        List<Integer> numbers = Stream.generate(() -> new SplittableRandom().nextInt(1000))
                .limit(10)
                .toList();


        // 2）将生产出的数据，闭包到Seq中。
        Seq<Integer> myStream1 = numbers::forEach;


        // 3）消费数据
        myStream1.consume(System.out::println);
    }

    @Test
    public void flatMap() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<List<Integer>> lists = List.of(list1, list2);

        Seq<List<Integer>> listSeq = lists::forEach;
        Seq<Integer> integerSeq = listSeq.flatMap(SequenceTest::seq);

        integerSeq.consume(System.out::println);
        integerSeq.take(3).consume(System.out::println);
    }

    @Test
    public void mapTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        // 已绑定this 的方法引用 (将 list 闭包了。使用 c(即 consumer) 对元素进行 消费。 具体的消费动作交给 调用者 扩展)
//        Seq<Integer> myStream = c -> list.forEach(c);
        Seq<Integer> myStream = list::forEach;


        // 一、 map的实现
        // Integer -> String -> Long -> BigDecimal
        //
        Seq<BigDecimal> mapAndThanForEach = myStream
                // 1）调用完此方法，function 包装进了一层Seq匿名内部类 ; 这是第一层
                .map(
                        t -> t + "---"
                )
                // 2）在以上 Seq内部类的基础上，再包装一层Seq匿名内部类; 这是第二层
                .map(
                        t -> Long.valueOf(t.replace("---", ""))
                )
                // 2）在以上 Seq内部类的基础上，再包装一层Seq匿名内部类; 这是第三层。
                .map(
                        BigDecimal::new
                );

        // 二、消费元素(实际调用者，是被层层包装过的匿名类)
        // 1） 遍历 元素
        // 2） map: integer -》String
        // 3） consumer: 打印 String
        mapAndThanForEach.consume(System.out::println);

        System.out.println("class");

        mapAndThanForEach.consume(e -> System.out.println(e.toString() + " " + e.getClass().toString()));

    }

    @Test
    public void underscoreToCamelTest() {
        String a = Seq.underscoreToCamel("aeb_afa_bbb");
        System.out.println(a);
    }

    @Test
    public void zipDemo1() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        Seq<Integer> myStream = list1::forEach;

        List<Integer> list2 = List.of(4, 5, 6);

        List<String> result = myStream.zip(list2, (arg1, arg2) -> arg1 + "-" + arg2).toList();

        System.out.println(result);
    }

    @Test
    public void zipDemo2() {

        Seq<Function<Integer, String>> seq = c2 -> {

            // 这里的 c2 为 zip 的 consumer
            System.out.println("客户端方法zipDemo2() 接收的 c2的地址值: " + c2);

            Function<Integer, String> function = num -> num + "-";
            System.err.println("客户端方法zipDemo2() 里面 定义的function的地址值: " + function);


            while (true) {
                c2.accept(function);
            }

        };
        System.err.println("客户端方法zipDemo2() 里面 的Seq的地址值: " + seq);


        List<String> result = seq.zip(List.of(4, 5, 6), Function::apply).toList();
        System.out.println(result);
    }


    @Test
    public void nestConsumerTest() {
        // Integer -> String
        Function<Integer, String> function3 = t -> {
            System.out.println("Integer -> String");
            return t + "===111";
        };
        // String -> Long
        Function<String, Long> function2 = e -> {
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


    static <T> Seq<T> unit(T t) {
        return c -> c.accept(t);
    }


    static Seq<Integer> seq(List<Integer> list) {
        return list::forEach;
    }

    @Test
    public void test23123() {
        List<Integer> integers = List.of(1, 2, 3);

        Iterator<Integer> iterator1 = integers.iterator();
        if (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        Iterator<Integer> iterator2 = integers.iterator();
        if (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

    }


    @Test
    public void stream2Seq() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        Seq<Integer> seq = stream::forEach;
    }


    @Test
    public void seq2Stream() {
        Stream<Integer> stream = Stream.of(1, 2, 3);

        Seq<Integer> seq = stream::forEach;


        Stream<Integer> stream1 = Seq.stream(seq);

        List<Integer> list = stream1.toList();
        System.out.println(list);

    }


    @Test
    public void ioDemo() throws FileNotFoundException {
        Stream<String> lines = new BufferedReader(new InputStreamReader(new FileInputStream("filePath"))).lines();

    }

    @Test
    public void ioTest() {
        Seq<String> seq = c -> {
            // 1) 构建数据源
            try (BufferedReader reader = Files.newBufferedReader(Paths.get("filePath"))) {
                String s;
                // 2）遍历读数据
                while ((s = reader.readLine()) != null) {
                    // 3）yield 元素
                    c.accept(s);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }


}
