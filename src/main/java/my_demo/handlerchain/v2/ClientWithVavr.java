package my_demo.handlerchain.v2;

import io.vavr.Function1;

/**
 * <p> 使用Vavr框架  改写责任链 </p>
 *
 * https://segmentfault.com/a/1190000019686060
 * https://juejin.cn/post/6844903876945002509
 * https://blog.csdn.net/ssehs/article/details/105831916
 *
 * 深入理解 Java 函数式编程（4）： 使用 Vavr 进行函数式编程
 * https://zhuanlan.zhihu.com/p/74681840
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/24
 * </pre>
 */
public class ClientWithVavr {

    public static void main(String[] args) {

        // 处理器1
        Function1<String, String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        // 处理器2
        Function1<String, String> spellCheckProcessing = (String text) -> text.replace("labda", "lambda");
        // 职责链
        Function1<String, String> chain = headerProcessing.compose(spellCheckProcessing);


        // 链式处理
        String result = chain.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }


}
