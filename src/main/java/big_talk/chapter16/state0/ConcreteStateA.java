package big_talk.chapter16.state0;

public class ConcreteStateA extends State 
{
    public void handle(Context context) {
        context.setState(new ConcreteStateB());
    }
}

//具体状态类B

