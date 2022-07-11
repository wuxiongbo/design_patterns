package my_demo.handlerchain.v2;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * <p>使用 lambda表达式  进行函数式编程，改写责任链</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/24
 * </pre>
 */
public class ClientWithLambda {
    public static void main(String[] args) {
        test();
    }

    private static void test(){
        // 处理器1
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        // 处理器2
        UnaryOperator<String> spellCheckProcessing = (String text) -> text.replace("labda", "lambda");


        // 职责链
        Function<String, String> chain = headerProcessing.andThen(spellCheckProcessing);
        // 链式处理
        String result = chain.apply("Aren't labdas really sexy?!!");
        System.out.println(result);


        // 处理器x
        UnaryOperator<String> xxxxxProcessing = (String text) -> text.concat("hhhh");
        // 添加 处理器x
        chain = chain.andThen(xxxxxProcessing);


        // 链式处理
        String result1 = chain.apply("Aren't labdas really sexy?!!");
        System.out.println(result1);
    }


    private static void test0(){
        // 处理器1
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        // 处理器2
        UnaryOperator<String> spellCheckProcessing = (String text) -> text.replace("labda", "lambda");
        // 处理器x
        UnaryOperator<String> xxxxxProcessing = (String text) -> text.concat("hhhh");

        // 职责链
        Function<String, String> chain = headerProcessing
                .andThen(spellCheckProcessing)
                .andThen(xxxxxProcessing);


        // 链式处理
        String result1 = chain.apply("Aren't labdas really sexy?!!");
        System.out.println(result1);
    }



}