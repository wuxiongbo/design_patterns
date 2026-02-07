package big_talk.chapter13.builder3;
import java.awt.Graphics;
import javax.swing.JFrame;

class PersonThinBuilder extends PersonBuilder {
    
    public PersonThinBuilder(Graphics g){
        super(g);
    }

    public void buildHead(){
        g.drawOval(150, 120, 30, 30);   //头
    }
    public void buildBody(){
        g.drawRect(160, 150, 10, 50);   //身体
    }
    public void buildArmLeft(){
        g.drawLine(160, 150, 140, 200); //左手
    }
    public void buildArmRight(){
        g.drawLine(170, 150, 190, 200); //右手
    }
    public void buildLegLeft(){
        g.drawLine(160, 200, 145, 250); //左脚
    }
    public void buildLegRight(){
        g.drawLine(170, 200, 185, 250); //右脚 
    }
}

//胖小人建造者

