package my_demo.decorator.demo1.decorator.impl;

import my_demo.decorator.demo1.component.Component;
import my_demo.decorator.demo1.decorator.Decorator;

/**
 * <p> 具体装饰器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }


    // 代码类似 ConcreteDecoratorA 所以省略

}
