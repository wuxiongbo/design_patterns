package my_demo.handlerchain.v2;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/24
 * </pre>
 */
public class ClientWithLambda {
    public static void main(String[] args) {

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
        // 添加处理器
        chain = chain.andThen(xxxxxProcessing);


        // 链式处理
        String result1 = chain.apply("Aren't labdas really sexy?!!");
        System.out.println(result1);

    }
}