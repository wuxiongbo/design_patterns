package big_talk.chapter23.command0;

public class ConcreteCommand extends Command{
    public ConcreteCommand(Receiver receiver){
        super(receiver);
    }

    public void excuteCommand(){
        receiver.action();
    }
}

