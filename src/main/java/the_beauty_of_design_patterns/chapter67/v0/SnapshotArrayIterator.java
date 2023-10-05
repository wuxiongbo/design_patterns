package the_beauty_of_design_patterns.chapter67.v0;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public class SnapshotArrayIterator<E> implements Iterator<E> {

    private int cursor;

    private ArrayList<E> snapshot;

    public SnapshotArrayIterator(ArrayList<E> arrayList) {

        this.cursor = 0;

        // 浅拷贝
        this.snapshot = new ArrayList<>();
        this.snapshot.addAll(arrayList);
    }

    @Override
    public boolean hasNext() {
        return cursor < snapshot.size();
    }

    @Override
    public E next() {
        E currentItem = snapshot.get(cursor);
        cursor++;
        return currentItem;
    }

}
