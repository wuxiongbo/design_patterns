package my_demo.command.demo2.concretecommand;

import my_demo.command.demo2.command.Command;
import my_demo.command.demo2.receiver.Receiver;

/**
 * <p>具体命令</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class ConcreteCommandA implements Command {

    // 任何类都可能成为一个接收者，只要它能够实现 命令(ConcreteCommand)的相应功能就行。
    private Receiver receiver;

    public ConcreteCommandA() {
        receiver = new Receiver();
        System.out.println("构建指令A");
    }

    @Override
    public void execute() {
        // 委托给 接受者
        receiver.actionB();
    }
}