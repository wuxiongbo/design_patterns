package design_patterns.chapter51.demo2;

import design_patterns.chapter51.dependence.ParamsWrapperDefinition;

/**
 * <p> 适配器  将有缺陷的外部系统进行二次封装。规范化接口</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/9
 * </pre>
 */
public class Client {
    public static void main(String[] args){

        // 直接使用 原始对象。  外部系统，不可修改
        CD cd = new CD();




        // 使用 “类”适配器。    适配器 的方法实现 委托给 原始对象
        // 使用 适配接口 多态 接收，从而屏蔽掉不应暴露的 原始对象接口。
        ITarget target = new CDAdaptor();
        // 调用目标方法
        target.function1();
        target.function2();
        target.fucntion3(new ParamsWrapperDefinition());
        target.function4();

    }
}
