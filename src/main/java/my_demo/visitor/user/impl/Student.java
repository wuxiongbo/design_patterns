package my_demo.visitor.user.impl;

import my_demo.visitor.user.User;
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
        visitor.visit(this);
    }


    //排名
    public int ranking() {
        return (int) (Math.random() * 100);
    }

}