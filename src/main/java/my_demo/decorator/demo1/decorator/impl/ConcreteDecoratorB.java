package my_demo.decorator.demo1.decorator.impl;

import my_demo.decorator.demo1.component.BaseComponent;
import my_demo.decorator.demo1.decorator.Decorator;

/**
 * <p> 具体装饰器 B功能</p>
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
        // ...增强逻辑
        operationFirstB();


        // 原始方法
        super.operation();


        // ...功能迭代新增逻辑
        anotherNewOperationB();
        // ...增强逻辑
        operationLastB();

    }


    private void operationFirstB(){
        System.out.println("operationFirst say ...DecoratorA");
    }
    private void operationLastB(){
        System.out.println("operationLast say ...DecoratorA");
    }
    //新功能
    private void anotherNewOperationB() {
        System.out.println("another operation ...DecoratorA");
    }

}
