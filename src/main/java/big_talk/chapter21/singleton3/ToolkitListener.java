package big_talk.chapter21.singleton3;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolkitListener implements ActionListener{
    private JFrame toolkit;
    
    public void actionPerformed(ActionEvent e) {
        if (toolkit == null || !toolkit.isVisible()){

            toolkit = new JFrame("工具箱");
            
            toolkit.setSize(150,300);
            toolkit.setLocation(100,100);
            toolkit.setResizable(false);
            toolkit.setAlwaysOnTop(true); //置顶
            toolkit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            toolkit.setVisible(true);
        }
    }
}

