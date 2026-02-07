package big_talk.chapter21.singleton4;
import javax.swing.JFrame;

public class Toolkit extends JFrame {

    private static Toolkit toolkit;

    private Toolkit(String title){
        super(title);
    }
    
    public static Toolkit getInstance(){
        //若toolkit不存在或隐藏时，可以实例化
        if (toolkit==null || !toolkit.isVisible()){
            toolkit = new Toolkit("工具箱");
            toolkit.setSize(150,300);
            toolkit.setLocation(100,100);
            toolkit.setResizable(false);
            toolkit.setAlwaysOnTop(true); //置顶
            toolkit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            toolkit.setVisible(true);
        }
        return toolkit;
    }
}

