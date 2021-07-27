package chapter16.demo1;


import chapter16.demo1.bo.ApiStatInfo;
import chapter16.demo1.bo.ApplicationContext;

/**
 * 开闭原则：
 *   软件实体（模块、类、方法等）应该  “对扩展开放、对修改关闭”
 *
 *   添加一个新的功能 应该是，
 *      在已有代码基础上 ‘扩展’ 代码（新增模块、类、方法等），
 *      而非 ‘修改’ 已有代码（修改模块、类、方法等）
 */

public class Main {
    public static void main(String[] args) {

        ApiStatInfo apiStatInfo = new ApiStatInfo();

        // ...省略设置apiStatInfo其他数据值的代码
        // 改动四：设置timeoutCount值
        apiStatInfo.setTimeoutCount(289);


        ApplicationContext
                //单例模式 初始化对象
                .getInstance()
                .getAlert()
                .check(apiStatInfo);

        // 以后维护 只需新增 AlertHandler 实现，修改 ApiStatInfo，修改 ApplicationContext


//        第一处改动是：在 ApiStatInfo 类中添加新的属性 timeoutCount。
//        第二处改动是：添加新的 TimeoutAlertHandler 类。
//        第三处改动是：在 ApplicationContext 类的 initializeBeans() 方法中，往 alert 对象中注册新的 timeoutAlertHandler。
//        第四处改动是：在使用 Alert 类的时候，需要给 check() 函数的入参 apiStatInfo 对象设置 timeoutCount 的值。

//        一、三、四 是修改，符合 开闭原则吗？

//        三、四  这两处改动都是在方法内部进行的，不管从哪个层面（模块、类、方法）来讲，都不能算是“扩展”，而是地地道道的“修改”。
//        不过，有些修改是在所难免的，是可以被接受的。为什么这么说呢？
//        我来解释一下。
//        1. 在重构之后的 Alert 代码中，我们的核心逻辑集中在 Alert 类及其各个 handler 中，
//           当我们在添加新的告警逻辑的时候，Alert 类完全不需要修改，而只需要扩展一个新 handler 类。
//           如果我们把 Alert 类及各个 handler 类合起来看作一个“模块”，那模块本身在添加新的功能的时候，完全满足开闭原则。

//        2. 我们要认识到，添加一个新功能，不可能任何模块、类、方法的代码都不“修改”，这个是做不到的。
//           类需要创建、组装、并且做一些初始化操作，才能构建成可运行的的程序，这部分代码的修改是在所难免的。
//           我们要做的是尽量让修改操作更集中、更少、更上层，尽量让 “最核心、最复杂的那部分逻辑代码” 满足 开闭原则。
    }
}