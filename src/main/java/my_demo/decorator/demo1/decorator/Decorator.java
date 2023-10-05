package my_demo.decorator.demo1.decorator;

import my_demo.decorator.demo1.component.BaseComponent;

/**
 * <p> 抽象装饰器：装饰器 也继承 抽象组件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public abstract class Decorator extends BaseComponent {
                                // 抽象装饰类  继承了  抽象组件。  这是可嵌套包装的关键


    // 聚合 将被装饰的 原始类
    protected BaseComponent baseComponent;


    // 依赖注入 待增强类（可能是原始类，也可能是被装饰器包裹的类）
    public Decorator(BaseComponent baseComponent) {
        this.baseComponent = baseComponent;
    }



    // 装饰器抽象的默认实现， 将实际操作 委托给 原始类
    @Override
    public void operation(){
        baseComponent.operation();
    }
}

