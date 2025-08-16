package design_patterns.chapter51.demo2;

import design_patterns.chapter51.dependence.ParamsWrapperDefinition;

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



}
