package chapter70.v1;

import chapter70.v1.snap.Snapshot;
import chapter70.v1.snap.SnapshotHolder;

import java.util.Scanner;

/**
 * <p>备忘录模式</p>
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

            if (input.equals(":list")) {
                // 展示文本内容
//                System.out.println(inputText.getText());
                System.out.println(inputText.toString());
            }
            else if (input.equals(":undo")) {
//                InputText snapshot = snapshotsHolder.popSnapshot();
//                inputText.setText(snapshot.getText());

                // 快照 出栈
                Snapshot snapshot = snapshotsHolder.popSnapshot();

                // 撤回文本内容
                inputText.restoreSnapshot(snapshot);
            }
            else {

                // 快照 压栈
                // snapshotsHolder.pushSnapshot(inputText);
                snapshotsHolder.pushSnapshot(inputText.createSnapshot());

                // 追加文本内容
                inputText.append(input);
            }

        }
    }
}
