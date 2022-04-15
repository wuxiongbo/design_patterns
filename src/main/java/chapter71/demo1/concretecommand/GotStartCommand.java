package chapter71.demo1.concretecommand;

import chapter71.demo1.command.Command;

/**
 * <p>ConcreteCommand</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class GotStartCommand implements Command {

    // 省略成员变量——receiver
    // Private Receiver receiver；

    public GotStartCommand(/*依赖注入 receiver */) {
        //...
    }

    /**
     * 关键业务是，这个函数里面的逻辑
     */
    @Override
    public void execute() {

        // 将实现，委托给 receiver 。让其 执行相应的逻辑
        // receiver.xxxMethod(/*parameters*/);

    }
}
