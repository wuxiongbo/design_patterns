package my_demo.visitor.interviewee.impl;

import my_demo.visitor.interviewee.User;
import my_demo.visitor.visitor.Visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Xander Wu
 * @date 2022/11/3 11:17
 */
public class Teacher extends User {

    public Teacher(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    @Override
    public void accept(Visitor visitor) {
        // 我只负责接收 访问者，至于 是谁访问、访问我的什么数据。我不关注。 这部分 具体行为(实现) ，由访问者自己决定
        visitor.visit(this);
    }


    // 升本率
    public double entranceRatio() {
        return BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
