package chapter62.demo1.v4;

import chapter62.demo1.v4.handlerchain.HandlerChain;
import chapter62.demo1.v4.handler.concrete.HandlerA;
import chapter62.demo1.v4.handler.concrete.HandlerB;

/**
 * <p> 职责链模式  使用模板模式重构</p>
 *
 * 在 GoF 给出的定义中，如果处理器链上的某个处理器能够处理这个请求，那就 “不会” 继续往下传递 请求。
 *
 * 实际上，职责链模式还有一种变体，那就是请求会被所有的处理器都处理一遍，不存在中途终止的情况。
 *
 * 这种变体也有两种实现方式：
 *      用 链表 存储处理器 和 用 数组 存储处理器，
 *      跟上面的两种实现方式类似，只需要稍微修改即可。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class Application {

    public static void main(String[] args) {

        HandlerChain chain = new HandlerChain();

        // 扩展点
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());

        chain.handle();
    }
}
