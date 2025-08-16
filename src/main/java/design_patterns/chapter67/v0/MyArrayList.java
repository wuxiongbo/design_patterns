package design_patterns.chapter67.v0;

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
public class MyArrayList<E> extends ArrayList<E> {

    @Override
    public Iterator<E> iterator() {
        return new SnapshotArrayIterator<>(this);
    }

}
