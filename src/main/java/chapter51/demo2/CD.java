package chapter51.demo2;

/**
 * <p>封装有缺陷的接口设计</p>
 *
 * 这个类来自外部sdk，我们无权修改它的代码
 *
 * 我们依赖的外部系统在接口设计方面有缺陷（比如包含大量静态方法），引入之后会影响到我们自身代码的可测试性。
 * 为了隔离设计上的缺陷，我们希望对外部系统提供的接口进行二次封装，抽象出更好的接口设计，这个时候就可以使用适配器模式了。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class CD {
    //...
    // 静态方法。是设计缺陷
    public static void staticFunction1() {
        //...
        System.out.println("staticFunction1()");
    }

    public void uglyNamingFunction2() {
        //...
        System.out.println("uglyNamingFunction2()");
    }

    public void tooManyParamsFunction3(int paramA, int paramB /*, ... */ ) {
        //...
        System.out.println("tooManyParamsFunction3()");
    }

    public void lowPerformanceFunction4() {
        //...
        System.out.println("lowPerformanceFunction4()");
    }
    //...
}
