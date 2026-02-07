package big_talk.chapter13.builder3;
import java.awt.Graphics;
import javax.swing.JFrame;

abstract class PersonBuilder {
    protected Graphics g;

    public PersonBuilder(Graphics g){
        this.g = g;
    }

    public abstract void buildHead();       //头
    public abstract void buildBody();       //身体
    public abstract void buildArmLeft();    //左手
    public abstract void buildArmRight();   //右手
    public abstract void buildLegLeft();    //左脚
    public abstract void buildLegRight();   //右脚
}

//瘦小人建造者

