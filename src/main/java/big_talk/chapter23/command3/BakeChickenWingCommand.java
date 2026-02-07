package big_talk.chapter23.command3;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class BakeChickenWingCommand extends Command{
    public BakeChickenWingCommand(Barbecuer receiver){
        super(receiver);
    }

    public void excuteCommand(){
        receiver.bakeChickenWing();
    }
}

//服务员类

