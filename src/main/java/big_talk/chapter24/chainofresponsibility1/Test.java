package big_talk.chapter24.chainofresponsibility1;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        Manager manager = new Manager("金利");
        Manager director = new Manager("宗剑");
        Manager generalManager = new Manager("钟精励");

        Request request = new Request();
        request.setRequestType("加薪");
        request.setRequestContent("小菜请求加薪");
        request.setNumber(10000);

        manager.getResult("经理", request);
        director.getResult("总监", request);
        generalManager.getResult("总经理", request);

        Request request2 = new Request();
        request2.setRequestType("请假");
        request2.setRequestContent("小菜请假");
        request2.setNumber(3);

        manager.getResult("经理", request2);
        director.getResult("总监", request2);
        generalManager.getResult("总经理", request2);
        
        System.out.println();
        System.out.println("**********************************************");
    }
}

//申请

