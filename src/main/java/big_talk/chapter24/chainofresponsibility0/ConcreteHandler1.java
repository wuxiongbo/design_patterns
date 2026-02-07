package big_talk.chapter24.chainofresponsibility0;

public class ConcreteHandler1 extends Handler{
    public void handleRequest(int request){
        if (request >=0 && request < 10){
            System.out.println(this.getClass().getSimpleName()+" 处理请求 "+request);
        }
        else if (successor != null){
            successor.handleRequest(request);
        }
    }
}

