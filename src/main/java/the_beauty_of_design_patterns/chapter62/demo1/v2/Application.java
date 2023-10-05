package the_beauty_of_design_patterns.chapter62.demo1.v2;

import the_beauty_of_design_patterns.chapter62.demo1.v2.handlerchain.HandlerChain;
import the_beauty_of_design_patterns.chapter62.demo1.v2.handler.concrete.HandlerA;
import the_beauty_of_design_patterns.chapter62.demo1.v2.handler.concrete.HandlerB;

/**
 * <p>  职责链模式——使用模板模式重构</p>
 *
 * 针对上个版本存在的问题，我们对代码进行重构，
 *
 * 利用  模板模式 ，将调用 successor.handle() 的逻辑从具体的处理器类中剥离出来，放到抽象父类中。
 * 这样， 具体的处理器类 只需要实现自己的业务逻辑就可以了。
 *
 * HandlerChain 和 Application 代码则保持不变
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
