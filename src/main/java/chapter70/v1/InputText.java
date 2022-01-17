package chapter70.v1;

import chapter70.v1.snap.Snapshot;

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

    // 恢复文本
    public void restoreSnapshot(Snapshot snapshot) {

        this.text.replace(0, this.text.length(),
                // 从快照中获得文本
                snapshot.getText());

    }

    // 生成快照
    public Snapshot createSnapshot() {
        return new Snapshot(text.toString());
    }

}
