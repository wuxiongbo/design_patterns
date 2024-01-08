package big_talk_design_patten.chapter19.component0.component.impl;

import big_talk_design_patten.chapter19.component0.component.Component;

/**
 * @author bear
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Cannot add to a leaf.");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("Cannot remove from a leaf.");
    }

    @Override
    public void display(int depth) {

        // 叶节点的具体显示方法，此处是显示其名称和级别
        for (var i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);


    }
}
