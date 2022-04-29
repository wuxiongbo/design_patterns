package my_demo.command.demo2.invoker;

import my_demo.command.demo2.command.Command;

/**
 * <p>调用者</p>
 *
 * 指令执行者
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Invoker {

    // TODO 这里可以是一个列表
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call() {
        System.out.println("调用者执行命令command...");
        command.execute();
    }
}
