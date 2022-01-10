package my_demo.command.demo2;

import my_demo.command.demo2.command.Command;
import my_demo.command.demo2.concretecommand.ConcreteCommandA;
import my_demo.command.demo2.concretecommand.ConcreteCommandB;
import my_demo.command.demo2.invoker.Invoker;

/**
 * <p>指令模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class CommandPattern {

    public static void main(String[] args) {

        executeCommand(new ConcreteCommandA());

        System.out.println("==========================");

        executeCommand(new ConcreteCommandB());

    }

    static void executeCommand(Command cmd){

        Invoker ir = new Invoker(cmd);

        System.out.println("客户 访问 调用者Invoker 的call()方法...");
        ir.call();
    }

}
