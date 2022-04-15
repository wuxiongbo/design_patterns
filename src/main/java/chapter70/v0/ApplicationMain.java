package chapter70.v0;

import java.util.Scanner;

/**
 * <p>备忘录模式</p>
 *
 * 用户输入 文本 时，程序将其追加存储在内存文本中；
 * 用户输入“:list”，程序在命令行中输出内存文本的内容；
 * 用户输入“:undo”，程序会撤销上一次输入的文本，也就是从内存文本中将上次输入的文本删除掉。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class ApplicationMain {
    public static void main(String[] args) {

        InputText inputText = new InputText();

        SnapshotHolder snapshotsHolder = new SnapshotHolder();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.next();

            // 展示
            if (input.equals(":list")) {
                // 展示 文本内容
                System.out.println(inputText.getText());
            }
            // 撤回
            else if (input.equals(":undo")) {
                // 快照 出栈
                InputText snapshot = snapshotsHolder.popSnapshot();

                // 撤回 文本内容
                inputText.setText(snapshot.getText());
            }
            // 追加
            else {
                // 快照 压栈
                snapshotsHolder.pushSnapshot(inputText);

                // 追加 文本内容
                inputText.append(input);
            }
        }
    }
}
