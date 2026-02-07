package big_talk.chapter18.memento2;

import lombok.Getter;
import lombok.Setter;
public class GameRole {
    //生命力
    @Getter
    @Setter
    private int vitality;
    //攻击力
    @Getter
    @Setter
    private int attack;
    //防御力
    @Getter
    @Setter
    private int defense;
    //状态显示
    public void displayState(){
        System.out.println("角色当前状态：");
        System.out.println("体力："+this.vitality);
        System.out.println("攻击力："+this.attack);
        System.out.println("防御力："+this.defense);
        System.out.println();
    }

    //获得初始状态(数据通常来自本机磁盘或远程数据接口)
    public void getInitState(){
        this.vitality = 100;
        this.attack = 100;
        this.defense = 100;
    }

    //战斗(在与Boss大战后游戏数据损耗为0)
    public void fight(){
        this.vitality = 0;
        this.attack = 0;
        this.defense = 0;
    }

    //保存角色状态
    public RoleStateMemento saveState(){
        return new RoleStateMemento(this.vitality,this.attack,this.defense);
    }

    //恢复角色状态
    public void recoveryState(RoleStateMemento memento){
        this.setVitality(memento.getVitality());
        this.setAttack(memento.getAttack());
        this.setDefense(memento.getDefense());
    }
}

//角色状态存储箱

