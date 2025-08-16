package big_talk.chapter28.visitor0.element;

import big_talk.chapter28.visitor0.visitor.Visitor;

public abstract class Element {
    public abstract void accept(Visitor visitor);
}
