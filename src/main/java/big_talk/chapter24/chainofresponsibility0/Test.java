package big_talk.chapter24.chainofresponsibility0;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        int[] requests = { 2, 5, 14, 22, 18, 3, 27, 20 };

        for(int request : requests) {
            h1.handleRequest(request);
        }
 
        System.out.println();
        System.out.println("**********************************************");
    }
}

