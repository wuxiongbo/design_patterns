package chapter65;

import chapter65.iterator.ArrayIterator;
import chapter65.iterator.Iterator;

import java.util.ArrayList;

/**
 * <p>迭代器模式</p>
 *
 *
 * 迭代器模式（Iterator Design Pattern），也叫作  ‘游标模式’（Cursor Design Pattern）。 它用来遍历 集合对象 。
 * 这里说的 “集合对象” 也可以叫    “容器” 、“聚合对象” ，
 * 实际上，就是包含一组对象的对象，比如数组、链表、树、图、跳表。
 *
 * 迭代器模式将 集合对象 的 遍历操作 从 集合类 中拆分出来，放到 '迭代器类' 中，让两者的 职责更加单一。
 *
 *
 * UML 图
 *
 * @startuml
 * interface Collection
 * interface Iterator{
 *     boolean hasNext()
 *     void next()
 *     E currentItem()
 * }
 * class ConcreteCollection
 * class ConcreteIterator
 *
 *
 * Collection  <|.. ConcreteCollection : 实现
 * Iterator <|.. ConcreteIterator : 实现
 * ConcreteCollection <--o ConcreteIterator : 聚合
 *
 * @enduml
 *
 *
 *
 * 在示例代码实现中，我们需要将 ‘待遍历的容器对象’ ，通过  构造函数  传递给  迭代器类。
 * 实际上，为了 封装 迭代器的创建细节，我们可以在容器中定义一个 iterator() 方法，来创建对应的迭代器。
 * 为了能实现基于接口而非实现编程，我们还需要将这个方法定义在 List 接口中。
 *
 * public interface List<E> {
 *   Iterator iterator();
 *   //...省略其他接口函数...
 * }
 *
 * public class ArrayList<E> implements List<E> {
 *   //...
 *   public Iterator iterator() {
 *     return new ArrayIterator(this);
 *   }
 *   //...省略其他代码
 * }
 *
 *
 * UML图：
 * https://note.youdao.com/s/5rZiOV00
 *
 *
 * 为什么 给容器 设计对应的 迭代器？
 *    单一职责、开闭原则(基于接口而非实现编程)
 * 1）通过拆分降低容器类的复杂度
 * 2）基于接口编程，方便替换遍历算法
 *
 *
 *
 * 迭代器模式主要作用是
 * 解耦 容器代码 和 遍历代码，这也印证了我们前面多次讲过的 “应用 设计模式 ，主要的目的是 ———— 解耦”。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Demo {
    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();
        names.add("xzg");
        names.add("wang");
        names.add("zheng");


        Iterator<String> iterator = new ArrayIterator<>(names);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }


    }
}