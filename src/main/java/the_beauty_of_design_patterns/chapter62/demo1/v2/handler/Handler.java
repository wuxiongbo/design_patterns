package the_beauty_of_design_patterns.chapter62.demo1.v2.handler;

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
    protected Handler successor = null;


    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }


    /**
     * 模板方法
     *
     * 注意，这里用 Final 修饰方法。
     *
     * final来修饰  '类'、 '方法' 、'属性'  都表示其不可变，也就是说  类 不可继承，方法 不可重写，属性 不可覆盖。
     *
     * 如果使用final来修饰方法，那么表示该方法不能被重写，
     *
     * 也就是说，这里加 final 的目的是， 让 父类中 final 修饰的handle()方法 不可被 子类 重写
     */
    public final void handle(/* 消息上下文 */) {

        boolean handled = doHandle(/* 消息上下文 */);


        // 在 ‘当前处理器’ 判断 是否将调用动作 传递 给 ‘下一个处理器’
        if (successor != null && !handled) {
            successor.handle(/* 消息上下文 */);
        }

        // 不用返回判断结果
    }

    // 算法骨架
    protected abstract boolean doHandle(/* 消息上下文 */);

}
