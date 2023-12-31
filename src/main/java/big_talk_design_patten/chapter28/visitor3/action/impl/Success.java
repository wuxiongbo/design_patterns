package big_talk_design_patten.chapter28.visitor3.action.impl;

import big_talk_design_patten.chapter28.visitor3.action.Action;
import big_talk_design_patten.chapter28.visitor3.person.impl.Man;
import big_talk_design_patten.chapter28.visitor3.person.impl.Woman;

// 成功 状态
public class Success extends Action {
    public void getManConclusion(Man concreteElementA) {
        System.out.println(concreteElementA.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，背后多半有一个伟大的女人。");
    }

    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println(concreteElementB.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，背后大多有一个不成功的男人。");
    }
}
