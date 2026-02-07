package big_talk.chapter16.state3;

class Work {

    private State current;
    
    public Work(){
        current = new ForenoonState();  
    }
    //设置状态
    public void setState(State value) {
        this.current = value;
    }
    //写代码的状态
    public void writeProgram() {
        this.current.writeProgram(this);
    }

    //当前的钟点
    private int hour;
    public int getHour(){
        return this.hour;
    }
    public void setHour(int value){
        this.hour = value;
    }

    //当前工作是否完成
    private boolean workFinished = false;
    public boolean getWorkFinished(){
        return this.workFinished;
    }
    public void setWorkFinished(boolean value){
        this.workFinished = value;
    }
}

