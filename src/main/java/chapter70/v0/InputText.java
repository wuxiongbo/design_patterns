package chapter70.v0;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class InputText {
    private StringBuilder text = new StringBuilder();

    // 展示文本
    public String getText() {
        return text.toString();
    }

    // 追加文本
    public void append(String input) {
        text.append(input);
    }

    // 设置文本
    public void setText(String text) {
        this.text.replace(0, this.text.length(), text);
    }
}
