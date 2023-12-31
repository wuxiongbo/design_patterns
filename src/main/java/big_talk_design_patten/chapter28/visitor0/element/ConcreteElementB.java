package big_talk_design_patten.chapter28.visitor0.element;

import big_talk_design_patten.chapter28.visitor0.visitor.Visitor;

public class ConcreteElementB extends Element {
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }

}
