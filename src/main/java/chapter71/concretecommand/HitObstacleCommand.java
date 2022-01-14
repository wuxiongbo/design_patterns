package chapter71.concretecommand;

import chapter71.command.Command;

/**
 * <p>ConcreteCommand</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class HitObstacleCommand implements Command {

    // 省略成员变量. receiver

    public HitObstacleCommand(/*数据, receiver */) {
        //...
    }


    @Override
    public void execute() {
        // 执行相应的逻辑
    }
}
