package big_talk.chapter23.command3;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

abstract class Command {
    protected Barbecuer receiver;

    public Command(Barbecuer receiver){
        this.receiver = receiver;
    }
    //执行命令
    public abstract void excuteCommand();
}

//烤羊肉命令类

