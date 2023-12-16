package my_demo.prototype.v1;

import my_demo.prototype.v1.QuestionBankController;
import org.junit.jupiter.api.Test;

/**
 * @author Xander Wu
 * @date 2023/12/5 09:49
 */
public class DemoTest {
    @Test
    public void test_QuestionBankController() {
        QuestionBankController questionBankController = new QuestionBankController();

        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));

    }

}
