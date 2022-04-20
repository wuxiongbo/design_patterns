package my_demo.mediator.colleague.items.impl;

import my_demo.mediator.colleague.AbsColleague;
import my_demo.mediator.mediator.AbsMediator;
import my_demo.mediator.colleague.items.ITax;

/**
 * 同事类：税费
 * @Author: Battle Bear
 * @Date: 2022/4/20 21:54
 * @Description:
 */
public class  Tax extends AbsColleague implements ITax {
    public Tax(AbsMediator mediator) {
        super(mediator);
    }
    @Override
    public void raise() {
        super.mediator.up(this);
    }
    @Override
    public void drop() {
        super.mediator.down(this);
    }
}
