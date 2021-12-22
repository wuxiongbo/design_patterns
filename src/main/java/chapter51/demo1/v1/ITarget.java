package chapter51.demo1.v1;

/**
 * <p> 适配目标 </p>
 *
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


    // "类"适配器: 基于 '继承'
    static void main(String[] args){
        ITarget target = new Adaptor();
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