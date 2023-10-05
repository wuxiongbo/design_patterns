package the_beauty_of_design_patterns.chapter62.demo1.v4;

import the_beauty_of_design_patterns.chapter62.demo1.v4.handler.concrete.HandlerA;
import the_beauty_of_design_patterns.chapter62.demo1.v4.handler.concrete.HandlerB;
import the_beauty_of_design_patterns.chapter62.demo1.v4.handlerchain.HandlerChain;

/**
 * <p> 职责链模式 变体————链表方式</p>
 *
 * 在 GoF 给出的定义中，如果处理器链上的某个处理器能够处理这个请求，那就 “不会” 继续往下传递 请求。
 *
 * 实际上，职责链模式还有一种变体，那就是
 *      请求会被  所有的处理器  都处理一遍，不存在中途终止的情况。
 *
 *
 * 这种变体也有两种实现方式：
 *      用 '链表' 存储处理器 和 用 '数组' 存储处理器，
 *
 * 职责链模式 变体，跟之前的两种实现方式类似，只需要 稍微修改 即可。
 *
 *
 * 这里给出基于 链表结构 的实现
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class Application {

    public static void main(String[] args) {

        // 责任链 构造器
        HandlerChain chain = new HandlerChain();

        // 扩展点。 处理器的创建 交给客户端
        HandlerA handlerA = new HandlerA();
        HandlerB handlerB = new HandlerB();

        // 构建责任链
        chain.addHandler(handlerA);
        chain.addHandler(handlerB);



        // 提交请求
        // 方式一：直接通过 责任链构造器 调用
        chain.handle(/* 消息上下文 */);
        // 方式二：通过 链表 的头结点调用。
        handlerA.handle(/* 消息上下文 */);

    }
}
