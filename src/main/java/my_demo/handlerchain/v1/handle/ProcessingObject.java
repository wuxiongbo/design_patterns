package my_demo.handlerchain.v1.handle;

/**
 * <p>处理器 抽象</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/24
 * </pre>
 */
public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handler(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handler(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}