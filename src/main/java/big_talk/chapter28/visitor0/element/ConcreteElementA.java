package big_talk.chapter28.visitor0.element;

import big_talk.chapter28.visitor0.visitor.Visitor;

public class ConcreteElementA extends Element {
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }

}
