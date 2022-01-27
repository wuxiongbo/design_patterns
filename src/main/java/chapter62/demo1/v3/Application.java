package chapter62.demo1.v3;

import chapter62.demo1.v3.handlerchain.HandlerChain;
import chapter62.demo1.v3.handler.concrete.HandlerA;
import chapter62.demo1.v3.handler.concrete.HandlerB;

/**
 * <p> 责任链 </p>
 *
 * 责任链 第二种实现方式   ‘数组’ 结构
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

        HandlerChain chain = new HandlerChain();

        // 扩展点。 处理器的创建 交给客户端
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());

        chain.handle();
    }
}
