package my_demo.mediator.colleague.items.impl;

import my_demo.mediator.colleague.AbsColleague;
import my_demo.mediator.AbsMediator;
import my_demo.mediator.colleague.items.IPosition;

/**
 * 同事类：职位
 * @Author: Battle Bear
 * @Date: 2022/4/20 21:52
 * @Description:
 */
public class Position extends AbsColleague implements IPosition {
    public Position(AbsMediator mediator) {
        super(mediator);
    }

    @Override
    public void promote() {
        super.mediator.up(this);
    }

    @Override
    public void demote() {
        super.mediator.down(this);
    }
}