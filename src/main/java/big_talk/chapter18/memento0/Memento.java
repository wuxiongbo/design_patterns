package big_talk.chapter18.memento0;

class Memento {

    private String state;

    public Memento (String state){
        this.state = state;
    }
    
    public String getState(){
        return this.state;
    }
    public void setState(String value){
        this.state = value;
    }
}

//管理者

