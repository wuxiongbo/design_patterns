package my_demo.decorator.demo1.decorator.impl;

import my_demo.decorator.demo1.component.BaseComponent;
import my_demo.decorator.demo1.decorator.Decorator;

/**
 * <p> 具体装饰器 A功能 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ConcreteDecoratorA extends Decorator {  // 装饰器 继承了 抽象装饰类


    public ConcreteDecoratorA(BaseComponent baseComponent){
        super(baseComponent);
    }


    // 装饰器 对原始类的操作，进行装饰增强
    @Override
    public void operation() {

        operationFirst();


        // 原始方法
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