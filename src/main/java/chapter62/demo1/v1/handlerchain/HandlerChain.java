package chapter62.demo1.v1.handlerchain;

import chapter62.demo1.v1.handler.Handler;

/**
 * <p> 处理器链 </p>
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

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }

}
