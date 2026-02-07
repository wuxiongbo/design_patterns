package big_talk.chapter25.mediator1;

class Iraq extends Country {
    public Iraq(UnitedNations unitedNations) {
        super(unitedNations);
    }

    public void declare(String message) {
        this.unitedNations.declare(message, this);
    }

    public void getMessage(String message) {
        System.out.println("伊拉克获得对方信息:" + message);
    }
}

//中介者类

