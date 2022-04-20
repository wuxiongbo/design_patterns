package my_demo.mediator.colleague.items.impl;

import my_demo.mediator.colleague.AbsColleague;
import my_demo.mediator.AbsMediator;
import my_demo.mediator.colleague.items.ISalary;

/**
 * 同事类：工资
 * @Author: Battle Bear
 * @Date: 2022/4/20 21:53
 * @Description:
 */

public class Salary extends AbsColleague implements ISalary {
    public Salary(AbsMediator mediator) {
        super(mediator);
    }
    @Override
    public void increaseSalary() {
        super.mediator.up(this);
    }
    @Override
    public void decreaseSalary() {
        super.mediator.down(this);
    }
}
