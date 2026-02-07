package big_talk.chapter23.command2;

class Waiter{
    private Command command;

    //设置订单
    public void setOrder(Command command){
        this.command = command;
    }

    //通知执行
    public void notifyCommand(){
        command.excuteCommand();
    }
}


//烤肉串者

