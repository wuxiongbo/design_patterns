package chapter19.demo2.v2;

/**
 * <p> 依赖注入（DI）</p>
 *
 * 那到底什么是依赖注入呢？我们用一句话来概括就是：
 * 不通过 new() 的方式在类内部创建依赖类对象，而是将依赖的类对象在外部创建好之后，通过构造函数、函数参数等方式传递（或注入）给类使用。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class Main {
    public static void main(String[] args){
        //使用Notification
        // 在外部 将 MessageSender new出来
        MessageSender messageSender = new MessageSender();
        // 通过构造方法 将 messageSender 传递给 Notification。  对于 Notification 而言，就实现了 "依赖注入"
        Notification notification = new Notification(messageSender);
    }
}
