package the_beauty_of_design_patterns.chapter42.demo1;

import the_beauty_of_design_patterns.chapter41.dependence.OrderVo;

/**
 * <p> 单例对 OOP 特性的支持不友好 </p>
 *
 * 1. 硬编码
 * 单例模式书写简洁、使用方便，在代码中，我们不需要创建对象，直接通过类似 IdGenerator.getInstance().getId() 这样的方法来调用就可以了。
 * 但是，这种使用方法有点类似硬编码（hard code），会带来诸多问题。
 *
 * 2. 对 OOP 特性的支持不友好
 * 我们知道，OOP 的四大特性是封装、抽象、继承、多态。
 * 单例这种设计模式对于其中的抽象、继承、多态都支持得不好。
 *
 *
 *
 * 为什么这么说呢？
 *
 * 我们还是通过 IdGenerator 这个例子来讲解
 *
 * IdGenerator 的使用方式违背了 “基于接口而非实现” 的设计原则，也就违背了广义上理解的 OOP 的抽象特性。
 *
 * 1)硬编码
 * 如果未来某一天，我们希望针对不同的业务采用不同的 ID 生成算法。比如，订单 ID 和用户 ID 采用不同的 ID 生成器来生成。
 * 为了应对这个需求变化，我们需要修改所有用到 IdGenerator 类的地方，这样代码的改动就会比较大。
 *
 * 2)OOP支持不友好
 * 除此之外，单例对继承、多态特性的支持也不友好。
 * 这里我之所以会用“不友好”这个词，而非“完全不支持”，是因为从理论上来讲，单例类也可以被继承、也可以实现多态，
 * 只是实现起来会非常奇怪，会导致代码的可读性变差。
 * 不明白设计意图的人，看到这样的设计，会觉得莫名其妙。
 *
 * 所以，一旦你选择将某个类设计成到单例类，
 *   也就意味着 放弃了‘继承’和‘多态’这两个强有力的面向对象特性，
 *   也就相当于损失了可以应对未来需求变化的扩展性。
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class OrderController {
    public void create(OrderVo order) {
        // ...省略业务逻辑代码...
        Logger.getInstance().log("Created a order: " + order.toString());
    }
}
