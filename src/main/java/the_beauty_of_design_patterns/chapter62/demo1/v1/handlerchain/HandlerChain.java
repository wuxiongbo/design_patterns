package the_beauty_of_design_patterns.chapter62.demo1.v1.handlerchain;

import the_beauty_of_design_patterns.chapter62.demo1.v1.handler.Handler;

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

    // 记录  ‘链头’ 、 ‘链尾’。  其中，记录 ‘链尾’ 是为了方便添加 处理器
    private Handler head = null;
    private Handler tail = null;



    public void addHandler(Handler handler) {

        // 确保添加的是 ‘单个结点’ ，而不是 ‘链’
        handler.setSuccessor(null);

        // 如果是首次添加 ‘结点’ ，则初始化  链头、链尾  这两个指针
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }

        // 在链尾 追加 新的结点。
        tail.setSuccessor(handler);

        // 更新链尾
        tail = handler;
    }


    public void handle(/* 消息上下文 */) {
        if (head != null) {
            // 从 ‘头结点’ 开始处理请求。
            head.handle();
        }
    }

}
