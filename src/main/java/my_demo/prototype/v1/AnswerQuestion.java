package my_demo.prototype.v1;

import lombok.Data;

/**
 * @author Xander Wu
 * @date 2023/12/5 09:44
 */
@Data
public class AnswerQuestion {

    private String name;  // 问题
    private String key;   // 答案

    public AnswerQuestion() {
    }

    public AnswerQuestion(String name, String key) {
        this.name = name;
        this.key = key;
    }

    // ...get/set
}
