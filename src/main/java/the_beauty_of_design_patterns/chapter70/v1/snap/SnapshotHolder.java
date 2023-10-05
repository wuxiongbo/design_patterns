package the_beauty_of_design_patterns.chapter70.v1.snap;

import java.util.Stack;

/**
 * <p>备忘录模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class SnapshotHolder {

//    private Stack<InputText> snapshots = new Stack<>();

    private Stack<Snapshot> snapshots = new Stack<>();


    public Snapshot popSnapshot() {
        return snapshots.pop();
    }


    public void pushSnapshot(Snapshot snapshot) {
        snapshots.push(snapshot);
    }

}
