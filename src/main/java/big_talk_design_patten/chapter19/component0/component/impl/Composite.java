package big_talk_design_patten.chapter19.component0.component.impl;

import big_talk_design_patten.chapter19.component0.component.Component;

import java.util.ArrayList;

/**
 * 子部件
 * @author bear
 */
public class Composite extends Component {
    /**
     * 一个子对象集合, 用来存储其下属的 枝节点 和 叶节点
     */
    private final ArrayList<Component> children = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void display(int depth) {

        // 显示其枝节点名称和级别
        for (var i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);


        // 对其下级进行遍历
        for (Component item : children) {
            item.display(depth + 2);
        }

    }
}
