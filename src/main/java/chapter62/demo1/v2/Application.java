package chapter62.demo1.v2;

import chapter62.demo1.v2.handlerchain.HandlerChain;
import chapter62.demo1.v2.handler.concrete.HandlerA;
import chapter62.demo1.v2.handler.concrete.HandlerB;

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

        HandlerChain chain = new HandlerChain();

        // 扩展点。 处理器的创建 交给客户端
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());

        // 提交请求
        chain.handle();
    }
}
