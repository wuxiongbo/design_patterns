package big_talk.chapter28.visitor3.action.impl;

import big_talk.chapter28.visitor3.action.Action;
import big_talk.chapter28.visitor3.person.impl.Man;
import big_talk.chapter28.visitor3.person.impl.Woman;

// 失败 状态
public class Failing extends Action {
    public void getManConclusion(Man concreteElementA) {
        System.out.println(concreteElementA.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，闷头喝酒，谁也不用劝。");
    }

    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println(concreteElementB.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，眼泪汪汪，谁也劝不了。");
    }
}
