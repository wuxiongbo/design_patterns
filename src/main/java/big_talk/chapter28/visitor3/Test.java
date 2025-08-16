package big_talk.chapter28.visitor3;

import big_talk.chapter28.visitor3.action.impl.Amativeness;
import big_talk.chapter28.visitor3.action.impl.Failing;
import big_talk.chapter28.visitor3.action.impl.Marriage;
import big_talk.chapter28.visitor3.action.impl.Success;
import big_talk.chapter28.visitor3.person.impl.Man;
import big_talk.chapter28.visitor3.person.impl.Woman;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        // 在对象结构体中加入要对比的 "男人" 和 "女人"
        ObjectStructure o = new ObjectStructure();
        o.attach(new Man());
        o.attach(new Woman());

        // 查看 在各种状态下,男人和女人的反应


        // 成功时的反应
        Success v1 = new Success();
        o.display(v1);
        System.out.println("---");

        // 失败时的反应
        Failing v2 = new Failing();
        o.display(v2);
        System.out.println("---");

        // 恋爱时的反应
        Amativeness v3 = new Amativeness();
        o.display(v3);
        System.out.println("---");
        
        // 婚姻时的反应
        Marriage v4 = new Marriage();
        o.display(v4);
        System.out.println("---");

        System.out.println();
        System.out.println("**********************************************");
    }
}







