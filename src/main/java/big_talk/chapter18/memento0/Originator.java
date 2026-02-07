package big_talk.chapter18.memento0;

class Originator {
    
    //状态
    private String state;
    public String getState(){
        return this.state;
    }
    public void setState(String value){
        this.state = value;
    }

    //显示数据
    public void show(){
        System.out.println("State:"+this.state);
    }

    //创建备忘录
    public Memento createMemento(){
        return new Memento(this.state);
    }

    //恢复备忘录
    public void recoveryMemento(Memento memento){
        this.setState(memento.getState());
    }

}

//备忘录类

