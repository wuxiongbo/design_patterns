package my_demo.visitor.interviewee.impl;

import my_demo.visitor.interviewee.User;
import my_demo.visitor.visitor.Visitor;

/**
 * @author Xander Wu
 * @date 2022/11/3 11:18
 */
public class Student extends User {

    public Student(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    @Override
    public void accept(Visitor visitor) {
        // 我只负责接收 访问者，至于 是谁访问、访问我的什么数据。我不关注。 这部分 具体行为(实现) ，由访问者自己决定
        visitor.visit(this);
    }


    //排名
    public int ranking() {
        return (int) (Math.random() * 100);
    }

}