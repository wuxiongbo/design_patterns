package my_demo.decorator.demo1.decorator.impl;

import my_demo.decorator.demo1.component.BaseComponent;
import my_demo.decorator.demo1.decorator.Decorator;

/**
 * <p> 具体装饰器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ConcreteDecoratorB extends Decorator {  // 装饰器 继承了 抽象装饰类

    public ConcreteDecoratorB(BaseComponent baseComponent) {
        super(baseComponent);
    }


    // 装饰器 对原始类的操作，进行装饰增强
    @Override
    public void operation() {

//        operationFirst();

        super.operation();

//        anotherOperation();

//        operationLast();

    }

}
