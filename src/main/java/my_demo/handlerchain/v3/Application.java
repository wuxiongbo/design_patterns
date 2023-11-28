package my_demo.handlerchain.v3;

import my_demo.handlerchain.v3.handler.FunctionHandler;

/**
 * <p> 责任链 </p>
 *
 * 责任链 第二种实现方式  ‘数组’ 结构
 *
 * 代码如下所示。
 *
 * 这种实现方式更加简单。HandlerChain 类， 用 ‘数组’ 而非 ‘链表’ 来保存所有的 处理器，
 * 并且需要在 HandlerChain 的 handle() 函数中，依次调用 每个处理器的 handle() 函数。
 *
 *
 * 总结：
 * 链表结构 的 职责链模式， 请求的判断与传递，由 各个处理器 完成。
 * 数组结构 的 职责链模式， 请求的判断与传递，由 职责链    完成。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class Application {

    public static void main(String[] args) {

        // 责任链 构造器
        String result = FunctionHandler.<String>build()
                .addHandler(new FunctionHandler<>(str -> str.contains("1"), str -> str + "\n包含1"))
                .addHandler(new FunctionHandler<>(str -> str.contains("2"), str -> str + "\n包含2"))
                .addHandler(new FunctionHandler<>(str -> str.contains("3"), str -> str + "\n包含3"))
                .addHandler(new FunctionHandler<>(str -> str.contains("4"), str -> str + "\n包含4"))
                .handle("134");

        System.out.println(result);

    }
}
