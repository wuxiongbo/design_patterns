package big_talk.chapter18.memento0;

class Caretaker{

    private Memento memento;
    public Memento getMemento(){
        return this.memento;
    }
    public void setMemento(Memento value){
        this.memento = value;
    }
}

