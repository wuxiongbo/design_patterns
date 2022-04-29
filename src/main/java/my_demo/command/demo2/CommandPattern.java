package my_demo.command.demo2;

import my_demo.command.demo2.command.Command;
import my_demo.command.demo2.command.concretecommand.ConcreteCommandA;
import my_demo.command.demo2.command.concretecommand.ConcreteCommandB;
import my_demo.command.demo2.invoker.Invoker;

/**
 * <p>指令模式  命令模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class CommandPattern {

    public static void main(String[] args) {
        //执行命令A
        executeCommand(new ConcreteCommandA());

        System.out.println("==========================");

        //执行命令B
        executeCommand(new ConcreteCommandB());
    }

    static void executeCommand(Command cmd){

        // 执行者
        Invoker ir = new Invoker(cmd);

        System.out.println("客户 访问 调用者Invoker 的call()方法...");

        // 执行者调用
        ir.call();
    }

}
