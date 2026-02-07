package big_talk.chapter13.builder3;

class PersonDirector{

    private PersonBuilder pb;
    //初始化时指定需要建造什么样的小人
    public PersonDirector(PersonBuilder pb){
        this.pb=pb;
    }
               
    //根据用户的需要建造小人
    public void CreatePerson(){
        pb.buildHead();     //头
        pb.buildBody();     //身体
        pb.buildArmLeft();  //左手
        pb.buildArmRight(); //右手
        pb.buildLegLeft();  //左脚
        pb.buildLegRight(); //右脚
    }
}

