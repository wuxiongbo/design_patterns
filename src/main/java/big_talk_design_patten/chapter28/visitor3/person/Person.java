package big_talk_design_patten.chapter28.visitor3.person;

import big_talk_design_patten.chapter28.visitor3.action.Action;

/**
 * 人类抽象类
 * @author Xander Wu
 * @date 2023/12/10 15:27
 */
public abstract class Person {
    /**
     *  接受
     * '接受'方法就是一个双分派的操作，它得到执行的操作不仅决定于'状态'类的具体状态，还决定于它访问的'人'的类别。
     * @param visitor '状态'类
     */
    public abstract void accept(Action visitor);
}
