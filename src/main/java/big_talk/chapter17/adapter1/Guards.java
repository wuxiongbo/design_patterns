package big_talk.chapter17.adapter1;

class Guards extends Player {
    public Guards(String name){
        super(name);
    }

    public void attack(){
        System.out.println("后卫 "+this.name+" 进攻");
    }

    public void defense(){
        System.out.println("后卫 "+this.name+" 防守");
    }
}

