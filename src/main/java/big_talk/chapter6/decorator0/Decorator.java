package big_talk.chapter6.decorator0;

abstract class Decorator extends Component {

    protected Component component;

    //装饰一个Component对象
    public void SetComponent(Component component) {
        this.component = component;
    }

    //重写Operation()，实际调用component的Operation方法
    public void Operation() {
        if (component != null) {
            component.Operation();
        }
    }
}

//ConcreteDecoratorA类

