package big_talk.chapter18.memento2;

class RoleStateMemento {
    private int vitality;
    private int attack;
    private int defense;

    //将生命力、攻击力、防御力存入状态存储箱对象中
    public RoleStateMemento (int vitality,int attack,int defense){
        this.vitality = vitality;
        this.attack = attack;
        this.defense = defense;
    }   
     
    //生命力
    public int getVitality(){
        return this.vitality;
    }
    public void setVitality(int value){
        this.vitality = value;
    }
    //攻击力
    public int getAttack(){
        return this.attack;
    }
    public void setAttack(int value){
        this.attack = value;
    }
    //防御力
    public int getDefense(){
        return this.defense;
    }
    public void setDefense(int value){
        this.defense = value;
    }
}

//角色状态管理者

