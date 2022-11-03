package chapter62.demo1.v1.handler;

/**
 * <p>  处理器抽象类 </p>
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public abstract class Handler {

    /**
     * successor 继承者
     * 用来记录  下一个 处理器。   由此可见，是个 单向连表 结构
     */
    protected Handler successor = null;


    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }


    /**
     *
     */
    public abstract void handle();

}
