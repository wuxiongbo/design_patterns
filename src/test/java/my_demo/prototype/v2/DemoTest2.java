package my_demo.prototype.v2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Xander Wu
 * @date 2023/12/5 10:02
 */
public class DemoTest2 {
    @Test
    public void test_QuestionBank() throws CloneNotSupportedException {
        QuestionBankController questionBankController = new QuestionBankController();

        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }

    @Test
    public void test111(){
        ArrayList<Topic> topics1 = new ArrayList<>();

        HashMap<String, String> map01 = new HashMap<>();
        map01.put("A", "JAVA2 EE");
        map01.put("B", "JAVA2 Card");
        map01.put("C", "JAVA2 ME");
        map01.put("D", "JAVA2 HE");
        map01.put("E", "JAVA2 SE");

        topics1.add(new Topic(map01,"11"));
        topics1.add(new Topic(map01,"22"));
        topics1.add(new Topic(map01,"33"));


        ArrayList<Topic> topics1Clone = (ArrayList<Topic>)topics1.clone();
//        ArrayList<Topic> topics1Clone = new ArrayList<>();
//        Collections.copy(topics1, topics1Clone);

        for (Topic topic : topics1Clone) {

            System.out.println(System.identityHashCode(topic));
        }
        System.out.println(System.identityHashCode(topics1Clone));


        System.out.println("------------");



        for (Topic topic : topics1) {
            System.out.println(System.identityHashCode(topic));
        }
        System.out.println(System.identityHashCode(topics1));
    }

}
