package chapter62.demo1.v5.handler;

/**
 * <p>  处理器抽象类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public interface IHandler {

    // 返回处理结果。 从而 将 是否需要向下传递  交给 职责链去判断
    void handle();


    //  处理器，仅处理业务逻辑，不再负责请求的传递

}
