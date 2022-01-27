package chapter62.demo1.v1;

import chapter62.demo1.v1.handlerchain.HandlerChain;
import chapter62.demo1.v1.handler.concrete.HandlerA;
import chapter62.demo1.v1.handler.concrete.HandlerB;

/**
 * <p>职责链模式</p>
 *
 * 职责链模式 的英文翻译是 Chain Of Responsibility Design Pattern。
 * 在 GoF 的《设计模式》中，它是这么定义的：
 *      Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request.
 *      Chain the receiving objects and pass the request along the chain until an object handles it.
 *
 * 翻译成中文就是：
 *      将 “请求” 的 ‘发送’ 和 ‘接收’ 解耦，让多个 “接收对象” 都有机会 处理 这个 “请求”。
 *      将这些 “接收对象” 串成一条 链 ，并沿着这条 链 传递这个 请求，
 *      直到 链 上的某个接收对象 能够处理它为止。
 *
 *
 * 核心概念：
 *    在职责链模式中，多个处理器（也就是刚刚定义中说的“接收对象”）依次处理同一个请求。
 *    一个 ‘请求’ 先经过 ‘A 处理器’ 处理 ，
 *    然后再把 ‘请求’ 传递给 ‘B 处理器’ ，
 *    B 处理器处理完后，再将 ‘请求’ 传递给 ‘C 处理器’ ，
 *    以此类推，形成一个‘链条’。
 *    ‘链条’上  每个处理器各自承担各自的处理职责，所以叫作 职责链模式。
 *
 *
 * 职责链模式有多种实现方式，我们这里介绍两种比较常用的。
 *
 * 第一种实现方式：  ‘链表’ 结构
 *
 * 如下所示。
 *
 * 其中，
 *   1)Handler 是 所有 处理器类 的 ‘抽象父类’ ，handle() 是抽象方法。
 *
 *   2)每个具体的处理器类（HandlerA、HandlerB）的 handle() 函数的代码结构类似，
 *      如果，它 能处理  该请求，就不继续往下传递；
 *      如果，它 不能处理 该请求，则交由  后面的处理器  来处理（也就是调用 successor.handle()）。
 *
 *   3)HandlerChain 是 处理器链，从 数据结构 的角度来看，它就是一个记录了链头、链尾的链表。
 *     其中，记录 链尾 是为了 方便添加 处理器。
 *
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

        // 提交请求
        chain.handle();

    }
}
