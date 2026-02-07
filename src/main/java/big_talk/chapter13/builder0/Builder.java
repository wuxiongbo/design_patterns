package big_talk.chapter13.builder0;

import java.util.ArrayList;

abstract class Builder {
    public abstract void buildPartA();      //建造部件A
    public abstract void buildPartB();      //建造部件B
    public abstract Product getResult();    //得到产品
}



//具体建造者1

