package my_demo.decorator.demo1;

import my_demo.decorator.demo1.component.Component;
import my_demo.decorator.demo1.component.impl.ConcreteComponent;
import my_demo.decorator.demo1.decorator.impl.ConcreteDecoratorA;
import my_demo.decorator.demo1.decorator.Decorator;
import my_demo.decorator.demo1.decorator.impl.ConcreteDecoratorB;

/**
 * <p>装饰器模式： 客户端</p>
 *
 *
 * 定义：动态地给对象添加一些额外的 ‘职责’ 或者 ‘行为’ ，装饰器模式  相比于  继承子类  更为灵活
 *
 *
 * 装饰器模式组成：
 *
 *   1. 抽象 组件 (Component): 定义 要被装饰器装饰的对象的接口
 *   2. 具体 组件 (ConcreteComponent)：定义一个 要被装饰器装饰的对象，即 Component 的具体实现
 *
 *   3. 抽象 装饰器 (Decorator): 维护对 组件对象 和其子类组件的引用。  即，依赖注入 具体组件，并将功能实现  委托给  具体组件
 *   4. 具体 装饰器 (ConcreteDecorator)：向 组件 添加 新的职责。    即，装饰增强功能
 *
 *
 *   抽象组件                 Component<—— 聚合
 *                           /      \       |
 *                        泛化      泛化     |
 *                        /           \    <>
 *   具体组件   ConcreteComponent     Decorator            抽象装饰器
 *                                     |
 *                                    泛化
 *                                     |
 *                                 ConcreteDecorator      具体装饰器
 *
 *
 *                 被装饰类             装饰类
 *
 *
 *
 * https://zhuanlan.zhihu.com/p/25003369
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
        Decorator objDecoratorA = new ConcreteDecoratorA(c1);
        objDecoratorA .operation();

        System.out.println("------------------------------------------------");

        // 第二次装饰
        Decorator objDecoratorADecoratorB = new ConcreteDecoratorB(objDecoratorA);
        objDecoratorADecoratorB.operation();

    }
}
