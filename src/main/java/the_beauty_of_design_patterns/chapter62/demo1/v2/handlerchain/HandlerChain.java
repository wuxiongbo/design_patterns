package the_beauty_of_design_patterns.chapter62.demo1.v2.handlerchain;

import the_beauty_of_design_patterns.chapter62.demo1.v2.handler.Handler;

/**
 * <p> 处理器链 </p>
 *
 * 个人理解：
 * 本对象的职责更像 是 责任链 管理器。 可有可无。  放在 handler抽象类中，使用建造者模式 作为内部类构造器，也是可以的
 *
 * 单向连表
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class HandlerChain {

    // 记录 链头、链尾。  其中，记录 链尾 是为了方便添加处理器
    private Handler head = null;
    private Handler tail = null;



    // 添加 处理器
    public void addHandler(Handler handler) {

        handler.setSuccessor(null);

        // 首次添加，则初始化 链头、链尾 指针
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }


        tail.setSuccessor(handler);

        // 更新链尾
        tail = handler;
    }


    // 触发 一连串的 链表的调用动作，从头结点开始。 传递判断 放在  过滤器抽象。
    public void handle(/* 消息上下文 */) {
        if (head != null) {
            head.handle(/* 消息上下文 */);
        }
    }

}
