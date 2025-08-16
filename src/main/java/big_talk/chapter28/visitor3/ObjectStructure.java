package big_talk.chapter28.visitor3;

import big_talk.chapter28.visitor3.action.Action;
import big_talk.chapter28.visitor3.person.Person;

import java.util.ArrayList;

/**
 * 对象结构
 * 由于总是需要'男人'与'女人'在不同状态的对比，
 * 所以, 我们需要一个  '对象结构'类 ,来针对不同的'状态' 遍历 '男人' 与 '女人'， 得到不同的反应。
 */
public class ObjectStructure {
    private final ArrayList<Person> elements = new ArrayList<>();

    // 增加
    public void attach(Person element) {
        elements.add(element);
    }

    // 移除
    public void detach(Person element) {
        elements.remove(element);
    }

    /**
     * 查看显示
     *
     * Action 是多态
     * Person 也是多态
     * 这意味着, '接受'方法就是一个双分派的操作，
     * 它得到执行的操作 不仅取决于'状态'类的具体状态，还取决于它访问的'人'的类别。
     *
     * @param visitor '状态'类
     */
    public void display(Action visitor) {
        for (Person person : elements) {
            // 第一次分派
            person.accept(visitor);
        }
    }
}
