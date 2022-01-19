package chapter58;

import chapter58.concrete.ConcreteClass1;
import chapter58.framework.AbstractClass;

import java.util.AbstractList;

/**
 * <p> 模板模式 </p>
 *
 * 模板模式，全称是 模板方法设计模式，英文是 Template Method Design Pattern。
 *
 * 在 GoF 的《设计模式》一书中，它是这么定义的：
 *      Define the skeleton of an algorithm in an operation, deferring some steps to subclasses.
 *      Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm’s structure.
 *
 * skeleton 骨骼
 * deferring 推迟
 *
 * 翻译成中文就是：
 *      模板方法模式 在一个方法中 定义一个 ‘算法骨架’ ，并将某些步骤推迟到 ‘子类’ 中实现。
 *      模板方法模式 可以让 ‘子类’ 在不改变算法整体结构的情况下，重新定义算法中的某些步骤。
 *
 * 这里的“算法”，我们可以理解为广义上的“业务逻辑”，并不特指数据结构和算法中的“算法”。
 * 这里的“算法骨架” 就是“模板”，包含 “算法骨架” 的方法就是“模板方法”，这也是 模板方法模式 名字的由来。
 *
 *
 *
 * 模板模式作用一：复用
 *
 * 例一： InputStream
 * Java IO 类库中，有很多类的设计用到了模板模式，比如 InputStream、OutputStream、Reader、Writer。我们拿 InputStream 来举例说明一下。
 * InputStream 相关代码  {@link java.io.InputStream#read(byte[], int, int)}
 * 在代码中，read() 函数是一个模板方法，定义了读取数据的整个流程，并且暴露了一个可以由子类来定制的抽象方法 read() 。
 *
 * 例二：AbstractList
 * {@link AbstractList#addAll(int, java.util.Collection)}
 * 在 Java AbstractList 类中，
 *      addAll() 函数可以看作模板方法，
 *      add() 是子类需要重写的方法，尽管没有声明为 abstract 的，但函数实现直接抛出了 UnsupportedOperationException 异常。
 *      所以，如果子类不重写 add()，是不能使用的。
 *
 *
 *
 * 模板模式作用二：扩展
 *
 * 例： HttpServlet
 * 我们现在来看，HttpServlet 的 service() 函数长什么样子。
 * {@link javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
 *
 * 从上面的代码中我们可以看出，HttpServlet 的 service() 方法就是一个模板方法，它实现了整个 HTTP 请求的执行流程，
 * doGet()、doPost() 是模板中可以由子类来定制的部分。
 * 实际上，这就相当于 Servlet 框架提供了一个扩展点（doGet()、doPost() 方法），
 * 让框架用户在不用修改 Servlet 框架源码的情况下，将业务代码通过扩展点镶嵌到框架中执行。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Client {

    public static void main(String[] args) {

        AbstractClass demo = new ConcreteClass1();

        demo.templateMethod();

    }

}
