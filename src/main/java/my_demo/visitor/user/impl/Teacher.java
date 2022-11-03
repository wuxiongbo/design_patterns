package my_demo.visitor.user.impl;

import my_demo.visitor.user.User;
import my_demo.visitor.visitor.Visitor;

import java.math.BigDecimal;

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
        visitor.visit(this);
    }


    // 升本率
    public double entranceRatio() {
        return BigDecimal.valueOf(Math.random() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
