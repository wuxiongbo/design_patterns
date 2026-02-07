package big_talk.chapter17.adapter2;

public class Forwards extends Player {
    public Forwards(String name){
        super(name);
    }

    public void attack(){
        System.out.println("前锋 "+this.name+" 进攻");
    }

    public void defense(){
        System.out.println("前锋 "+this.name+" 防守");
    }
}

//中锋

