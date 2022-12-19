package chapter62.demo1.v3;

import chapter62.demo1.v3.handler.concrete.HandlerA;
import chapter62.demo1.v3.handler.concrete.HandlerB;
import chapter62.demo1.v3.handlerchain.HandlerChain;

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
