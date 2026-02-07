package big_talk.chapter16.state0;

class ConcreteStateB extends State 
{
    public void handle(Context context) {
        context.setState(new ConcreteStateA());
    }
}

//上下文

