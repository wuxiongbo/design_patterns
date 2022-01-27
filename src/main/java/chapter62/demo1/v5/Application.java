package chapter62.demo1.v5;

import chapter62.demo1.v5.handler.concrete.HandlerA;
import chapter62.demo1.v5.handler.concrete.HandlerB;
import chapter62.demo1.v5.handlerchain.HandlerChain;

/**
 * <p> 责任链 变体 </p>
 *
 * 责任链变体 第二种实现方式   ‘数组’ 结构
 *
 * 在 GoF 给出的定义中，如果处理器链上的某个处理器能够处理这个请求，那就 “不会” 继续往下传递 请求。
 *
 * 实际上，职责链模式还有一种变体，那就是
 *      请求会被  所有的处理器  都处理一遍，不存在中途终止的情况。
 *
 *
 * 这种变体也有两种实现方式：
 *      用 链表 存储处理器 和 用 数组 存储处理器，
 *
 *
 * 这里给出基于 数组结构 的实现
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
