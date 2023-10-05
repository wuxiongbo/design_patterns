package the_beauty_of_design_patterns.chapter19.demo2.v3;

import the_beauty_of_design_patterns.chapter19.demo2.v3.impl.SmsSender;

/**
 * <p> 依赖注入（DI）</p>
 *
 * 继续优化，将MessageSender 抽象为接口
 *
 *
 * 存在的其他问题：
 *
 * 在采用依赖注入实现的 Notification 类中，虽然我们不需要用类似 hard code 的方式，在类内部通过 new 来创建 MessageSender 对象，
 * 但是，这个创建对象、组装（或注入）对象的工作仅仅是被移动到了更上层代码而已，还是需要我们程序员自己来实现。
 *
 * 在实际的软件开发中，一些项目可能会涉及几十、上百、甚至几百个类，类对象的创建和依赖注入会变得非常复杂。
 * 如果这部分工作都是靠程序员自己写代码来完成，容易出错且开发成本也比较高。
 * 而对象创建和依赖注入的工作，本身跟具体的业务无关，我们完全可以抽象成框架来自动完成。
 * 你可能已经猜到，这个框架就是“依赖注入框架”。
 *
 *
 * 解决思路：
 *
 * 我们只需要通过 “依赖注入框架” 提供的扩展点，简单配置一下所有需要创建的 类对象、类与类之间的依赖关系，
 * 就可以实现由框架来自动创建对象、管理 对象的生命周期、依赖注入等 原本需要程序员来做的事情。
 *
 * 实际上，现成的“依赖注入框架”有很多，比如 Google Guice、Java Spring、Pico Container、Butterfly Container 等。
 * 不过，如果你熟悉 Java Spring 框架，你可能会说，Spring 框架自己声称是 “控制反转容器”（Inversion Of Control Container）。
 *
 * 实际上，“依赖注入框架”、“控制反转容器”，这两种说法都没错。
 * 只是 “控制反转容器” 这种表述是一种非常宽泛的描述，“依赖注入框架” 的表述更具体、更有针对性。
 * 我们前面讲到，实现控制反转的方式有很多，除了依赖注入，还有模板模式等，而 Spring 框架的控制反转主要是通过依赖注入来实现的。
 * 不过这点区分并不是很明显，也不是很重要，你稍微了解一下就可以了。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class Main {
    public static void main(String[] args){

        //使用Notification

        //创建对象。  对象的创建 仅仅是被移动到了更上层代码而已。
        MessageSender messageSender = new SmsSender();

        //依赖注入
        Notification notification = new Notification(messageSender);

        notification.sendMessage("13918942177", "短信验证码：2346");

    }
}
