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

    // 不返回处理结果。 不再判断 是否需要将行为继续向下传递
    void handle();


    //  处理器，仅处理业务逻辑，
    //  不负责 对请求的传递

}
