package big_talk.chapter13.builder2;
import java.awt.Graphics;

public class PersonThinBuilder {
    private Graphics g;

    public PersonThinBuilder(Graphics g){
        this.g=g;
    }

    public void build(){
        g.drawOval(150, 120, 30, 30);   //头
        g.drawRect(160, 150, 10, 50);   //身体
        g.drawLine(160, 150, 140, 200); //左手
        g.drawLine(170, 150, 190, 200); //右手
        g.drawLine(160, 200, 145, 250); //左脚
        g.drawLine(170, 200, 185, 250); //右脚
    }
}

//胖小人建造者

