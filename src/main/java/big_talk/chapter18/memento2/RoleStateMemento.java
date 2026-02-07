package big_talk.chapter18.memento2;

import lombok.Getter;
import lombok.Setter;
public class RoleStateMemento {
    @Getter
    @Setter
    private final int vitality;
    @Getter
    @Setter
    private final int attack;
    @Getter
    @Setter
    private final int defense;

    //将生命力、攻击力、防御力存入状态存储箱对象中
    public RoleStateMemento (int vitality,int attack,int defense){
        this.vitality = vitality;
        this.attack = attack;
        this.defense = defense;
    }   
     
    //生命力
    //攻击力
    //防御力
}

//角色状态管理者

