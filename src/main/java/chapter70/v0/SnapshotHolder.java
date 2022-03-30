package chapter70.v0;

import java.util.Stack;

/**
 * <p> 备忘录 </p>
 * 用来记录 文本的 历史快照
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class SnapshotHolder {

    private Stack<InputText> snapshots = new Stack<>();

    // 出栈
    public InputText popSnapshot() {
        return snapshots.pop();
    }

    // 压栈
    public void pushSnapshot(InputText inputText) {


        InputText deepClonedInputText = new InputText();
        deepClonedInputText.setText(inputText.getText());

        snapshots.push(deepClonedInputText);
    }
}
