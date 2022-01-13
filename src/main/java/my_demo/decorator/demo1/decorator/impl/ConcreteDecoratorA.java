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
public class ConcreteDecoratorA extends Decorator {  // 装饰器 继承了 抽象装饰类


    public ConcreteDecoratorA(Component component){
        super(component);
    }


    // 对原始类的操作，进行装饰
    @Override
    public void operation() {

        operationFirst();

        super.operation();
        anotherOperation();

        operationLast();

    }


    private void operationFirst(){
        System.out.println("operationFirst say");
    }
    private void operationLast(){
        System.out.println("operationLast say");
    }
    //新功能
    private void anotherOperation() {
        System.out.println("another operation");
    }

}