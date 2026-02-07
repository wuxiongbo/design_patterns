package big_talk.chapter21.singleton4;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ToolkitListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {

        //Toolkit toolkit = new Toolkit("工具箱");
        
        Toolkit.getInstance();

    }
}

//工具箱类

