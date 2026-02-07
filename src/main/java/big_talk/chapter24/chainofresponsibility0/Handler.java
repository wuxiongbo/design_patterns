package big_talk.chapter24.chainofresponsibility0;

public abstract class Handler{
    protected Handler successor;

    //设置继任者
    public void setSuccessor(Handler successor){
        this.successor = successor;
    }

    public abstract void handleRequest(int request);
}

