package big_talk.chapter25.mediator1;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        UnitedNationsSecurityCouncil UNSC = new UnitedNationsSecurityCouncil();

        USA c1 = new USA(UNSC);
        Iraq c2 = new Iraq(UNSC);

        UNSC.setUSA(c1);
        UNSC.setIraq(c2);

        c1.declare("不准研制核武器，否则要发动战争！");
        c2.declare("我们没有核武器，也不怕侵略。");


 
        System.out.println();
        System.out.println("**********************************************");
    }
}

