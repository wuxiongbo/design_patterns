package big_talk.chapter27.interpreter0;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        Context context = new Context();
        ArrayList<AbstractExpression> list = new ArrayList<AbstractExpression>();
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());
        list.add(new TerminalExpression());
        list.add(new TerminalExpression());

        for (AbstractExpression exp : list) {
            exp.interpret(context);
        }

        System.out.println();
        System.out.println("**********************************************");
    }
}

//抽象表达式类

