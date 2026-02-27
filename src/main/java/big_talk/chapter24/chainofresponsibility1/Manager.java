package big_talk.chapter24.chainofresponsibility1;

public class Manager{
    protected String name;
    public Manager(String name){
        this.name = name;
    }

    public void getResult(String managerLevel,Request request){
        if ("经理".equals(managerLevel)){
            if ("请假".equals(request.getRequestType()) && request.getNumber()<=2) {
                System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，被批准");
            } else {
                System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，我无权处理");
            }
        } else if ("总监".equals(managerLevel)){
            if ("请假".equals(request.getRequestType()) && request.getNumber()<=5) {
                System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，被批准");
            } else {
                System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，我无权处理");
            }
        } else if ("总经理".equals(managerLevel)){
            if ("请假".equals(request.getRequestType())) {
                System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，被批准");
            } else if ("加薪".equals(request.getRequestType()) && request.getNumber()<=5000) {
                System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"元，被批准");
            } else if ("加薪".equals(request.getRequestType()) && request.getNumber()>5000) {
                System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"元，再说吧");
            }
        }
    }
}
