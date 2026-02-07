package big_talk.chapter18.memento2;

class RoleStateCaretaker{

    private RoleStateMemento memento;
    public RoleStateMemento getRoleStateMemento(){
        return this.memento;
    }
    public void setRoleStateMemento(RoleStateMemento value){
        this.memento = value;
    }
}

