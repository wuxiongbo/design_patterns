package big_talk.chapter25.mediator0;

class ConcreteMediator extends Mediator{
    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    public void setColleague1(ConcreteColleague1 value) {
        this.colleague1 = value; 
    }

    public void setColleague2(ConcreteColleague2 value) {
        this.colleague2 = value; 
    }

    public void send(String message, Colleague colleague)
    {
        if (colleague == colleague1) {
            colleague2.notify(message);
        }
        else {
            colleague1.notify(message);
        }
    }
}

