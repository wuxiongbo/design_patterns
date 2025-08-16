package design_patterns.chapter71.demo1.command.concretecommand;

import design_patterns.chapter71.demo1.command.Command;

/**
 * <p>ConcreteCommand</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class ArchiveCommand implements Command {

    // 省略成员变量——receiver
    // Private Receiver receiver；

    public ArchiveCommand(/*依赖注入 receiver */) {
        //...
    }


    /**
     * 关键业务是，这个函数里面的逻辑
     */
    @Override
    public void execute() {

        // 委托给 receiver 。执行相应的逻辑
        // receiver.xxxMethod(/*parameters*/);

    }


}
