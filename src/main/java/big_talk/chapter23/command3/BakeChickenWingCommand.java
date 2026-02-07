package big_talk.chapter23.command3;

public class BakeChickenWingCommand extends Command{
    public BakeChickenWingCommand(Barbecuer receiver){
        super(receiver);
    }

    public void excuteCommand(){
        receiver.bakeChickenWing();
    }
}

//服务员类

