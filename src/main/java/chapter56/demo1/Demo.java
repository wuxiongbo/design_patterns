package chapter56.demo1;

import chapter56.demo1.observer.impl.ConcreteObserverOne;
import chapter56.demo1.observer.impl.ConcreteObserverTwo;
import chapter56.demo1.subject.impl.ConcreteSubject;
import chapter56.dependence.Message;

/**
 * <p>描述类的信息</p>
 *
 * 观察者模式（Observer Design Pattern）也被称为发布订阅模式（Publish-Subscribe Design Pattern）。
 *
 * 在 GoF 的《设计模式》一书中，它的定义是这样的：
 *  Define a one-to-many dependency between objects so that when one object changes state,
 *  all its dependents are notified and updated automatically.
 *
 * 翻译成中文就是：在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。
 *
 *
 * 一般情况下，被依赖的对象叫作被观察者（Observable），依赖的对象叫作观察者（Observer）。
 * 不过，在实际的项目开发中，这两种对象的称呼是比较灵活的，有各种不同的叫法，
 * 比如：
 *   Subject-Observer、             Subject 主题      Observer 观察者
 *   Publisher-Subscriber、         Publisher 出版者   Subscriber 订阅者
 *   Producer-Consumer、
 *   EventEmitter-EventListener、   emitter 发射器
 *   Dispatcher-Listener。          Dispatcher  调度器、分配器
 *
 * 不管怎么称呼，只要应用场景符合刚刚给出的定义，都可以看作观察者模式。
 *
 *
 * 实际上，<stroe>观察者模式是一个比较抽象的模式</stroe>，根据不同的应用场景和需求，有完全不同的实现方式，待会我们会详细地讲到。
 * 现在，我们先来看其中最经典的一种实现方式。这也是在讲到这种模式的时候，很多书籍或资料给出的最常见的实现方式。
 * 具体的代码如下所示：
 *
 * 实际上，下面的代码算是观察者模式的“模板代码”，只能反映大体的设计思路。
 * 在真实的软件开发中，并不需要照搬上面的模板代码。
 * 观察者模式的实现方法各式各样，函数、类的命名等会根据业务场景的不同有很大的差别，
 * 比如
 *    register 函数还可以叫作 attach，
 *    remove 函数还可以叫作 detach 等等。
 *
 * 不过，万变不离其宗，设计思路都是差不多的。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Demo {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());

        subject.notifyObservers(new Message());
    }
}
