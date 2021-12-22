package my_demo.decorator.decorator;

import my_demo.decorator.component.Component;

/**
 * <p>抽象装饰器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public abstract class Decorator extends Component {

    // 将被装饰的原始类
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }


    // 将操作 委托给原始类
    @Override
    public void operation(){
        component.operation();
    }
}

