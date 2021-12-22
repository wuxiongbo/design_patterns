package chapter51.demo1.v2;


/**
 * <p> 适配目标 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public interface ITarget {

    void f1();

    void f2();

    void fc();

    //  "对象"适配器: 基于 '组合'
    static void main(String[] args){
        // 原始对象
        Adaptee adaptee = new Adaptee();

        // 依赖注入 原始对象
        ITarget target = new Adaptor(adaptee);
        target.f1();
        target.f2();
        target.fc();
    }
}
/*
fa()
f2()
fc()
 */