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
public class ConcreteCommandB implements Command {

    // 任何类都可能成为一个接收者。
    // 只要它能够实现 命令(ConcreteCommand)的相应功能就行。
    private Receiver receiver;

    public ConcreteCommandB() {
        receiver = new Receiver();
        System.out.println("构建 指令B");
    }

    @Override
    public void execute() {
        // 具体实现 委托给 接受者
        receiver.actionB();
    }
}
