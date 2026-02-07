package big_talk.chapter13.builder3;
import java.awt.Graphics;

public class PersonFatBuilder extends PersonBuilder {
    public PersonFatBuilder(Graphics g){
        super(g);
    }

    public void buildHead(){
        g.drawOval(250, 120, 30, 30);   //头
    }
    public void buildBody(){
        g.drawOval(245, 150, 40, 50);   //身体
    }
    public void buildArmLeft(){
        g.drawLine(250, 150, 230, 200); //左手
    }
    public void buildArmRight(){
        g.drawLine(280, 150, 300, 200); //右手
    }
    public void buildLegLeft(){
        g.drawLine(260, 200, 245, 250); //左脚
    }
    public void buildLegRight(){
        g.drawLine(270, 200, 285, 250); //右脚
    }
}

//指挥者

