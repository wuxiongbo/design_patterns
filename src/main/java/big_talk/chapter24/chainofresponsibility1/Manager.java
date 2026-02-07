package big_talk.chapter24.chainofresponsibility1;

public class Manager{
    protected String name;
    public Manager(String name){
        this.name = name;
    }

    public void getResult(String managerLevel,Request request){
        if (managerLevel == "经理"){
          if (request.getRequestType()=="请假" && request.getNumber()<=2)
            System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，被批准");
          else 
            System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，我无权处理");
        }
        else if (managerLevel == "总监"){
          if (request.getRequestType()=="请假" && request.getNumber()<=5)
            System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，被批准");
          else 
            System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，我无权处理");
        }
        else if (managerLevel == "总经理"){
          if (request.getRequestType()=="请假")
            System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"天，被批准");
          else if (request.getRequestType()=="加薪" && request.getNumber()<=5000)
            System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"元，被批准");
          else if (request.getRequestType()=="加薪" && request.getNumber()>5000)
            System.out.println(this.name+":"+request.getRequestContent()+" 数量:"+request.getNumber()+"元，再说吧");
        }
    }
}

