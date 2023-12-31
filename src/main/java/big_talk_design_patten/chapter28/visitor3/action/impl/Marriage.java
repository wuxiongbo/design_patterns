package big_talk_design_patten.chapter28.visitor3.action.impl;

import big_talk_design_patten.chapter28.visitor3.action.Action;
import big_talk_design_patten.chapter28.visitor3.person.impl.Man;
import big_talk_design_patten.chapter28.visitor3.person.impl.Woman;

// 结婚 状态
public class Marriage extends Action {
    public void getManConclusion(Man concreteElementA) {
        System.out.println(concreteElementA.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，感慨道：恋爱游戏终结时，‘有妻徒刑’遥无期。");
    }

    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println(concreteElementB.getClass().getSimpleName()
                + " " + this.getClass().getSimpleName() + "时，欣慰曰：爱情长跑路漫漫，婚姻保险保平安。");
    }
}
