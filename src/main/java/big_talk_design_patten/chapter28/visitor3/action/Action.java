package big_talk_design_patten.chapter28.visitor3.action;

import big_talk_design_patten.chapter28.visitor3.person.impl.Man;
import big_talk_design_patten.chapter28.visitor3.person.impl.Woman;

/**
 * 状态抽象类
 */
public abstract class Action {
    //得到男人结论或反应
    public abstract void getManConclusion(Man concreteElementA);

    //得到女人结论或反应
    public abstract void getWomanConclusion(Woman concreteElementB);
}
