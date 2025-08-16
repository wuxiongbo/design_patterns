package design_patterns.chapter71.demo1.command;

/**
 * <p>Command</p>
 * 命令对象，用来包裹函数
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public interface Command {
    /**
     * 执行命令
     */
    void execute();
}
