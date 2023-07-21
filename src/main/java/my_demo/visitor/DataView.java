package my_demo.visitor;

import my_demo.visitor.user.impl.Student;
import my_demo.visitor.user.impl.Teacher;
import my_demo.visitor.user.User;
import my_demo.visitor.visitor.impl.Parent;
import my_demo.visitor.visitor.impl.Principal;
import my_demo.visitor.visitor.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 校长、家长
 *    观察
 * 学生、老师
 *    的 视角点 不一样
 *
 * @author Xander Wu
 * @date 2022/11/3 11:21
 */
public class DataView {

    private static final Logger logger = LoggerFactory.getLogger(DataView.class);


    List<User> userList = new ArrayList<>();

    public DataView() {
        // 学生
        userList.add(new Student("谢飞机", "重点班", "一年一班"));
        userList.add(new Student("windy", "重点班", "一年一班"));
        userList.add(new Student("大毛", "普通班", "二年三班"));
        userList.add(new Student("Shing", "普通班", "三年四班"));

        // 老师
        userList.add(new Teacher("BK", "特级教师", "一年一班"));
        userList.add(new Teacher("娜娜Goddess", "特级教师", "一年一班"));
        userList.add(new Teacher("dangdang", "普通教师", "二年三班"));
        userList.add(new Teacher("泽东", "实习教师", "三年四班"));

    }

    // 展示
    public void show(Visitor visitor) {
        for (User user : userList) {
            user.accept(visitor);
        }
    }



    public static void main(String[] args) {
        DataView dataView = new DataView();

        logger.info("\r\n家长视角，观察访问：");
        dataView.show(new Parent());     // 家长

        logger.info("\r\n校长视角，观察访问：");
        dataView.show(new Principal());  // 校长
    }

}