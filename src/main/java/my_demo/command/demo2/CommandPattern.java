package my_demo.command.demo2;

import my_demo.command.demo2.command.Command;
import my_demo.command.demo2.concretecommand.ConcreteCommandA;
import my_demo.command.demo2.concretecommand.ConcreteCommandB;
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
        //构建命令A
        ConcreteCommandA commandA = new ConcreteCommandA();
        //执行命令A
        executeCommand(commandA);

        System.out.println("==========================");

        //构建命令B
        ConcreteCommandB commandB = new ConcreteCommandB();
        //执行命令B
        executeCommand(commandB);

    }

    static void executeCommand(Command cmd){

        Invoker ir = new Invoker(cmd);

        System.out.println("客户 访问 调用者Invoker 的call()方法...");

        // 调用者（指令执行者）  执行命令
        ir.call();
    }

}
