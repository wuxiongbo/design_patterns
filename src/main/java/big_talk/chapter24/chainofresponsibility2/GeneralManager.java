package big_talk.chapter24.chainofresponsibility2;

public class GeneralManager extends Manager{
    public GeneralManager(String name){
        super(name);
    }

    public void requestApplications(Request request){
        if ("请假".equals(request.getRequestType())) {
            System.out.println(this.name+":"+request.getRequestContent()+" 数量："+request.getNumber()+"天，被批准");
        } else if ("加薪".equals(request.getRequestType()) && request.getNumber()<=5000) {
            System.out.println(this.name+":"+request.getRequestContent()+" 数量："+request.getNumber()+"元，被批准");
        } else if ("加薪".equals(request.getRequestType()) && request.getNumber()>5000) {
            System.out.println(this.name+":"+request.getRequestContent()+" 数量："+request.getNumber()+"元，再说吧");
        }
    }
}
