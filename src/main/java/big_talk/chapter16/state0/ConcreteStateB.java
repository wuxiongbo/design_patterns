package big_talk.chapter16.state0;

public class ConcreteStateB extends State 
{
    public void handle(Context context) {
        context.setState(new ConcreteStateA());
    }
}

//上下文

