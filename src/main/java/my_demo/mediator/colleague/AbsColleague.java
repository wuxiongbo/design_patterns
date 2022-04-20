package my_demo.mediator.colleague;

import my_demo.mediator.AbsMediator;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 21:51
 * @Description:
 */
//抽象同事类
public abstract class AbsColleague {

    //每个同事都知道中介者
    protected AbsMediator mediator;

    public AbsColleague(AbsMediator mediator) {
        this.mediator = mediator;
    }

}