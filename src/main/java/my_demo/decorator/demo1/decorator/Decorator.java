package my_demo.decorator.demo1.decorator;

import my_demo.decorator.demo1.component.Component;

/**
 * <p> 抽象装饰器：装饰器 也继承 抽象组件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public abstract class Decorator extends Component {  // Decorator 继承 Component  ，与此同时

    // 将被装饰的 原始类
    protected Component component;
    // 同时，Decorator 与 Component  还是 聚合关系
    // Component 聚合 成 Decorator
    // Decorator 关联 Component


    public Decorator(Component component) {
        this.component = component;
    }


    // 将操作 委托给 原始类
    @Override
    public void operation(){
        component.operation();
    }
}

