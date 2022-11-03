package chapter70.v0;

/**
 * <p>备忘录模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class InputText {
    // 文本数据
    private StringBuilder text = new StringBuilder();

    // 展示 文本
    public String getText() {
        return text.toString();
    }

    // 追加 文本
    public void append(String input) {
        text.append(input);
    }

    // 设置 文本
    public void setText(String text) {
        this.text.replace(0, this.text.length(), text);
    }
}
