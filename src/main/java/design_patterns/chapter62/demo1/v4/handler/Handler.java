package design_patterns.chapter62.demo1.v4.handler;

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


    // 依赖注入 下一个处理器
    public void setSuccessor(Handler successor) {
        this.next = successor;
    }


    /**
     * <p>模板方法</p>
     *
     * 传递动作，由模板方法完成
     *
     * 注意，这里使用了 Final 关键字，来修饰方法。
     * final 关键字 ，修饰  '类'、 '方法' 、'属性' 都表示其值不可变，
     * 也就是说， "类" 不可继承，"方法" 不可重写，"属性" 不可覆盖。
     *
     * 此处 使用final来修饰方法，让此方法不能被重写，是为了防止 父类中的  handle()方法  被 子类 重写
     */
    public final void handle(/* 消息上下文 */) {

        // 责任链模式 的典型
//        boolean handled = doHandle();
//        if (successor != null && !handled) {
//            successor.handle(); // 向下传递
//        }


        // 算法骨架 (填充业务)
        doHandle(/* 消息上下文 */);


        // 责任链模式 的变体：
        // 无论成功与否，均将请求向下传递。 不再判断当前处理器是否成功处理
        if (next != null ) {
            next.handle(/* 消息上下文 */);  // 向下传递
        }

    }

    // 算法骨架。 模板
    protected abstract void doHandle(/* 消息上下文 */);

}
