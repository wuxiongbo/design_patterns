package big_talk.chapter23.command3;

class BakeMuttonCommand extends Command{
    public BakeMuttonCommand(Barbecuer receiver){
        super(receiver);
    }

    public void excuteCommand(){
        receiver.bakeMutton();
    }
}

//烤鸡翅命令类

