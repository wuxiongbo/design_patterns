package big_talk.chapter28.visitor0;

import big_talk.chapter28.visitor0.element.Element;
import big_talk.chapter28.visitor0.visitor.Visitor;

import java.util.ArrayList;

public class ObjectStructure {
    private final ArrayList<Element> elements = new ArrayList<>();

    public void attach(Element element) {
        elements.add(element);
    }

    public void detach(Element element) {
        elements.remove(element);
    }

    public void accept(Visitor visitor) {
        for (Element e : elements) {
            e.accept(visitor);
        }
    }
}
