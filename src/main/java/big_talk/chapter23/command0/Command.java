package big_talk.chapter23.command0;

abstract class Command {
    protected Receiver receiver;

    public Command(Receiver receiver){
        this.receiver = receiver;
    }
    //执行命令
    public abstract void excuteCommand();
}

//具体命令类

