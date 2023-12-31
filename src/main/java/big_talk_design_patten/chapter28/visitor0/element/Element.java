package big_talk_design_patten.chapter28.visitor0.element;

import big_talk_design_patten.chapter28.visitor0.visitor.Visitor;

public abstract class Element {
    public abstract void accept(Visitor visitor);
}
