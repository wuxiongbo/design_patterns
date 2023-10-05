package the_beauty_of_design_patterns.chapter51.demo2;

import the_beauty_of_design_patterns.chapter51.dependence.ParamsWrapperDefinition;

/**
 * <p>封装有缺陷的接口设计</p>
 *
 * 注意：适配器类的命名不一定非得末尾带Adaptor
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class CDAdaptor extends CD implements ITarget {
    //...
    @Override
    public void function1() {
        staticFunction1();
    }

    @Override
    public void function2() {
        super.uglyNamingFunction2();
    }

    @Override
    public void fucntion3(ParamsWrapperDefinition paramsWrapper) {

        super.tooManyParamsFunction3(paramsWrapper.getParamA(),paramsWrapper.getParamB() /* , ...*/);
    }

    @Override
    public void function4() {
        // ...reemployment it...
        // 重新实现
    }
}