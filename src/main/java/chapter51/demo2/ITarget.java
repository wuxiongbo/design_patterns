package chapter51.demo2;

import chapter51.dependence.ParamsWrapperDefinition;

/**
 * <p>封装有缺陷的接口设计</p>
 *
 * 使用适配器模式进行重构
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public interface ITarget {
    void function1();

    void function2();

    void fucntion3(ParamsWrapperDefinition paramsWrapper);

    void function4();

    //...


    /**
     *
     * 将有缺陷的外部系统进行二次封装。规范化接口
     *
     * @param args
     */
    static void main(String[] args){
        // 原始对象。  外部系统，不可修改
        CD cd = new CD();

        // “类”适配器。
        ITarget target = new CDAdaptor();

        // 调用目标方法
        target.function1();
        target.function2();
        target.fucntion3(new ParamsWrapperDefinition());
        target.function4();
    }
}
