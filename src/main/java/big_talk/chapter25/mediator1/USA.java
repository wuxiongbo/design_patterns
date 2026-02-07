package big_talk.chapter25.mediator1;

class USA extends Country {
    public USA(UnitedNations unitedNations) {
        super(unitedNations);
    }

    public void declare(String message) {
        this.unitedNations.declare(message, this);
    }

    public void getMessage(String message) {
        System.out.println("美国获得对方信息:" + message);
    }
}

