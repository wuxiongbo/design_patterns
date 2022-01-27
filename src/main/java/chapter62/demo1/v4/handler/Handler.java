package chapter62.demo1.v4.handler;

/**
 * <p>  处理器抽象类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public abstract class Handler {

    // 用来记录下一个 处理器。   由此可见，是个 单向连表 结构
    protected Handler next = null;


    public void setSuccessor(Handler successor) {
        this.next = successor;
    }


    /**
     * <p>模板方法</p>
     *
     * 传递动作，由模板方法完成
     *
     * 注意，这里用 Final 修饰方法。
     * final来修饰  '类'、 '方法' 、'属性'  都表示其值不可变，也就是说  类 不可继承，方法 不可重写，属性 不可覆盖。
     *
     * 如果使用final来修饰方法，那么表示该方法不能被重写，
     * 如果在父类中使用final 来修饰方法，那么该方法就 不可 被子类 重写
     */
    public final void handle() {

        // 责任链模式 的典型
//        boolean handled = doHandle();
//        if (successor != null && !handled) {
//            successor.handle(); // 向下传递
//        }


        // 算法骨架
        doHandle();


        // 责任链模式 的变体。
        // 无论成功与否，均将请求向下传递。 不再判断当前处理器是否成功处理
        if (next != null ) {
            next.handle();  // 向下传递
        }

    }

    // 算法骨架。 模板
    protected abstract void doHandle();

}
