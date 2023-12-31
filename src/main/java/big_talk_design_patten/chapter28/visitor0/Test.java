package big_talk_design_patten.chapter28.visitor0;

import big_talk_design_patten.chapter28.visitor0.element.ConcreteElementA;
import big_talk_design_patten.chapter28.visitor0.element.ConcreteElementB;
import big_talk_design_patten.chapter28.visitor0.visitor.ConcreteVisitor1;
import big_talk_design_patten.chapter28.visitor0.visitor.ConcreteVisitor2;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        ObjectStructure o = new ObjectStructure();
        o.attach(new ConcreteElementA());
        o.attach(new ConcreteElementB());

        ConcreteVisitor1 v1 = new ConcreteVisitor1();
        ConcreteVisitor2 v2 = new ConcreteVisitor2();

        o.accept(v1);
        o.accept(v2);


        System.out.println();
        System.out.println("**********************************************");
    }
}




