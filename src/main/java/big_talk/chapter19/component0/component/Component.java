package big_talk.chapter19.component0.component;

/**
 * 声明接口
 * @author bear
 */
public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    /**
     * 添加子部件
     * @param component 子部件
     */
    public abstract void add(Component component);

    /**
     * 移除子部件
     * @param component 子部件
     */
    public abstract void remove(Component component);

    /**
     * @param depth
     */
    public abstract void display(int depth);
}
