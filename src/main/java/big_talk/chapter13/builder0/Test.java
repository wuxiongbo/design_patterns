package big_talk.chapter13.builder0;

public class Test {

    public static void main(String[] args){

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println();  

        Director director = new Director();
        Builder b1 = new ConcreteBuilder1();
        Builder b2 = new ConcreteBuilder2();

        //指挥者用ConcreteBuilder1的方法来建造产品
        director.construct(b1); //创建的是产品A和产品B
        Product p1 = b1.getResult();
        p1.show();
        
        //指挥者用ConcreteBuilder2的方法来建造产品
        director.construct(b2); //创建的是产品X和产品Y
        Product p2 = b2.getResult();
        p2.show();

        System.out.println();
        System.out.println("**********************************************");

    }
}

//产品类

