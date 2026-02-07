package big_talk.chapter23.command0;

class Invoker{

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void executeCommand(){
        command.excuteCommand();
    }

}

