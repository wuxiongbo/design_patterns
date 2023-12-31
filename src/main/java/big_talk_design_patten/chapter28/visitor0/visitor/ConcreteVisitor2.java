package big_talk_design_patten.chapter28.visitor0.visitor;

import big_talk_design_patten.chapter28.visitor0.element.ConcreteElementA;
import big_talk_design_patten.chapter28.visitor0.element.ConcreteElementB;

public class ConcreteVisitor2 extends Visitor {
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println(concreteElementA.getClass().getSimpleName() + "被" + this.getClass().getSimpleName() + "访问");
    }

    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println(concreteElementB.getClass().getSimpleName() + "被" + this.getClass().getSimpleName() + "访问");
    }
}
