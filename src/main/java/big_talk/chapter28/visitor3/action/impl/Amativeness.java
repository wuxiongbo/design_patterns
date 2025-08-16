package big_talk.chapter28.visitor3.action.impl;

import big_talk.chapter28.visitor3.action.Action;
import big_talk.chapter28.visitor3.person.impl.Man;
import big_talk.chapter28.visitor3.person.impl.Woman;

// 恋爱 状态
public class Amativeness extends Action {
    public void getManConclusion(Man concreteElementA) {
        System.out.println(concreteElementA.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，凡事不懂也要装懂。");
    }

    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println(concreteElementB.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，遇事懂也装作不懂。");
    }
}
