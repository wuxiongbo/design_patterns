package the_beauty_of_design_patterns.chapter51.demo1.adaptor.v1;

import the_beauty_of_design_patterns.chapter51.demo1.itarget.ITarget;

/**
 * <p> 类 适配器 </p>
 *
 *
 * 这两种实现方式，在实际开发中，到底该如何选择使用哪一种呢？
 *
 * 判断的标准主要有两个，
 *      1） Adaptee 接口 的个数，
 *      2） Adaptee 和 ITarget 的契合程度。
 *
 *
 * 如果 Adaptee 接口 并不多，那两种实现方式都可以。
 *
 * 如果 Adaptee 接口 很多，
 *     1） Adaptee的接口定义 和 ITarget的接口定义 大部分都 相同，那我们推荐使用  类适配器，
 *         因为 Adaptor 复用父类 Adaptee 的接口，比起对象适配器的实现方式，Adaptor 的代码量要少一些。
 *
 *     2） Adaptee的接口定义 和 ITarget的接口定义 大部分都 不相同，那我们推荐使用 对象适配器，
 *         因为，组合结构相对于继承更加灵活。
 *
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Client {

    // "类"适配器: 基于 '继承'
    public static void main(String[] args){

        // 直接构造 "类"适配器  即可
        ITarget target = new Adaptor();

        target.f1();
        target.f2();
        target.fc();
    }
}
