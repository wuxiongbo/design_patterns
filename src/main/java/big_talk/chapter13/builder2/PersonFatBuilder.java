package big_talk.chapter13.builder2;
import java.awt.Graphics;

public class PersonFatBuilder {
    private final Graphics g;

    public PersonFatBuilder(Graphics g){
        this.g=g;
    }

    public void build(){
        g.drawOval(250, 120, 30, 30);   //头
        g.drawOval(245, 150, 40, 50);   //身体
        g.drawLine(250, 150, 230, 200); //左手
        g.drawLine(280, 150, 300, 200); //右手
        g.drawLine(260, 200, 245, 250); //左脚
        g.drawLine(270, 200, 285, 250); //右脚
    }
}

