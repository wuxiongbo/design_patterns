package my_demo.decorator;

import my_demo.decorator.component.Component;
import my_demo.decorator.component.impl.ConcreteComponent;
import my_demo.decorator.decorator.impl.ConcreteDecoratorA;
import my_demo.decorator.decorator.Decorator;

/**
 * <p>客户端</p>
 *
 *
 * 定义：动态地给对象添加一些额外的职责或者行为，装饰器模式  相比于 生成子类 更为灵活
 *
 *
 * 装饰器模式组成：
 *
 *   1. 抽象 组件 (Component): 定义 要被装饰器装饰的对象的接口
 *   2. 具体 组件 (ConcreteComponent)：定义一个 要被装饰器装饰的对象，即 Component 的具体实现
 *
 *   3. 抽象 装饰器 (Decorator): 维护对 组件对象 和其子类组件的引用。  即，依赖注入 并 委托  组件
 *   4. 具体 装饰器 (ConcreteDecorator)：向 组件 添加 新的职责。    即，装饰增强功能
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Client {
    public static void main(String[] args){

        // 原始组件
        Component c1 = new ConcreteComponent();

        // 第一次装饰
        Decorator decoratorA = new ConcreteDecoratorA(c1);
        decoratorA .operation();

        System.out.println("------------------------------------------------");

        // 第二次装饰
        Decorator decoratorBandA = new ConcreteDecoratorA(decoratorA);
        decoratorBandA.operation();

    }
}
