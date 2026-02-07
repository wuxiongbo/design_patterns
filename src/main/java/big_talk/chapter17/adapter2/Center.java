package big_talk.chapter17.adapter2;

public class Center extends Player {
    public Center(String name){
        super(name);
    }

    public void attack(){
        System.out.println("中锋 "+this.name+" 进攻");
    }

    public void defense(){
        System.out.println("中锋 "+this.name+" 防守");
    }
}

//后卫

