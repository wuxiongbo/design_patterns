package chapter71.concretecommand;

import chapter71.Command;

/**
 * <p>ConcreteCommand</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class ArchiveCommand implements Command {

    // 省略成员变量. receiver

    public ArchiveCommand(/*数据, receiver */) {
        //...
    }

    @Override
    public void execute() {
        // 执行相应的逻辑
    }
}
