package my_demo.decorator.decorator.impl;

import my_demo.decorator.component.Component;
import my_demo.decorator.decorator.Decorator;

/**
 * <p>具体装饰器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ConcreteDecoratorA extends Decorator {


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