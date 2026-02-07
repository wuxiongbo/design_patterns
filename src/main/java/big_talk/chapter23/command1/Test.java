package big_talk.chapter23.command1;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println();       

        Barbecuer boy = new Barbecuer();

        boy.bakeMutton();
        boy.bakeMutton();
        boy.bakeMutton();
        boy.bakeChickenWing();
        boy.bakeMutton();
        boy.bakeMutton();
        boy.bakeChickenWing();

        System.out.println();
        System.out.println("**********************************************");
    }
}

//烤肉串者

