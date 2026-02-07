package big_talk.chapter24.chainofresponsibility2;

abstract class Manager{
    protected String name;
    public Manager(String name){
        this.name = name;
    }

    //设置管理者上级
    protected Manager superior;
    public void setSuperior(Manager superior){
        this.superior = superior;
    }

    //请求申请
    public abstract void requestApplications(Request request);
}

//普通经理

