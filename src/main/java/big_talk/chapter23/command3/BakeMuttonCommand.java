package big_talk.chapter23.command3;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class BakeMuttonCommand extends Command{
    public BakeMuttonCommand(Barbecuer receiver){
        super(receiver);
    }

    public void excuteCommand(){
        receiver.bakeMutton();
    }
}

//烤鸡翅命令类

