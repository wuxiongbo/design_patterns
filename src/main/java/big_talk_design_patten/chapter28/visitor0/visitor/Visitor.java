package big_talk_design_patten.chapter28.visitor0.visitor;

import big_talk_design_patten.chapter28.visitor0.element.ConcreteElementA;
import big_talk_design_patten.chapter28.visitor0.element.ConcreteElementB;

public abstract class Visitor {
    public abstract void visitConcreteElementA(ConcreteElementA concreteElementA);

    public abstract void visitConcreteElementB(ConcreteElementB concreteElementB);
}
