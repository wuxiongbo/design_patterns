package chapter69;

import chapter69.polymorphism.ChildClass;
import chapter69.polymorphism.ParentClass;
import chapter69.polymorphism.SingleDispatchClass;

/**
 * <p> 编译时  重载，
 *     运行时  多态 </p>
 * ===========================================
 * 名词解释：
 *  分派：
 *    对方法版本的选择确定过程，我们称之为"分派"
 *  多分派：
 *    多个方法版本 选择 方法。 (方法签名不同)
 *  单分派：
 *    一个方法版本 选择 对象。 (方法签名相同)
 *
 * ===========================================
 * 分派（dispatch）：
 *  根据  对象的类型 而对 方法 进行的选择。
 *
 *  多分派： 选择的是方法
 *      根据  多个判断依据（即，参数 类型 和 个数），判断出 方法的版本。
 *
 *  单分派： 选择的是对象
 *      根据  实际引用类型，即，只有一个判断依据， 判断 方法版本
 *
 *  静态分派：
 *      发生在 "编译时" 的分派，例如：重载（overload）。
 *      因此，静态分派 通常是 '静态 多分派'
 *
 *  动态分派：
 *      发生在 "运行时" 的分派，例如：重写（overwrite）。
 *      因此，动态分派 通常是 '动态 单分派'
 *
 *  动态双分派：
 *      在 "运行时" ，依据 在两个实际类型中 判断 选择一个方法版本的 运行行为.
 *
 * ==============================================================
 *
 *  "访问者模式" 进行了两次 动态双分派：
 *      第一次，element 的 accept() 方法； 选择哪个 element
 *      第二次，visitor 的 visit() 方法；  选择哪个 visitor
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Main {

    public static void main(String[] args) {
        SingleDispatchClass dispatch = new SingleDispatchClass();

        ParentClass p = new ChildClass();

        // 执行 哪个对象的 f()方法，由 参数对象 “运行时” 的 实际类型决定
        dispatch.polymorphismFunction(p);

        System.out.println("-----------");
        // 执行 dispatch对象 的哪个 overloadFunction() 方法，由 参数对象 “编译时” 的 声明类型决定
        dispatch.overloadFunction(p);
        System.out.println("-----------");
        dispatch.overloadFunction((ChildClass)p);
    }
}
/*
代码执行结果:
I am ChildClass's f().
I am overloadFunction(ParentClass p).
 */