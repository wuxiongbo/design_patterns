package chapter62.demo1.v3.handlerchain.handler;

/**
 * <p>  处理器抽象类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public interface IHandler {

    boolean handle();

    //  处理器，仅处理业务逻辑，不再负责请求的传递
}
