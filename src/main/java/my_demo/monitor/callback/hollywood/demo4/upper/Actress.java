package my_demo.monitor.callback.hollywood.demo4.upper;

import my_demo.monitor.callback.hollywood.demo4.lower.IPerformer;

/**
 * <p>女演员</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Actress implements IPerformer {

    // 回调方法。
    // 被动接收消息通知
    @Override
    public void update(int i) {
        System.out.println("p1收到："+ i +"%");
    }

}
