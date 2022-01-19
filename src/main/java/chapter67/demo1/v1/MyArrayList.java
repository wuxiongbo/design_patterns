package chapter67.demo1.v1;


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

    private static final int DEFAULT_CAPACITY = 100;

    private int actualSize; // 没被删除标记的元素个数
    private int totalSize;  // 容器中元素的个数


    // 添加时间戳
    private long[] addTimestamps;
    // 删除时间戳
    private long[] delTimestamps;

    public MyArrayList() {
        super();

        // 忽略扩容问题
        this.addTimestamps = new long[DEFAULT_CAPACITY];
        this.delTimestamps = new long[DEFAULT_CAPACITY];
        this.totalSize = 0;
        this.actualSize = 0;
    }

    // 当元素被加入到集合中的时候，我们将 addTimestamp 设置为当前时间，将 delTimestamp 设置成最大长整型值（Long.MAX_VALUE）。
    @Override
    public boolean add(E obj) {
        addTimestamps[totalSize] = System.currentTimeMillis();
        delTimestamps[totalSize] = Long.MAX_VALUE;
        totalSize++;
        actualSize++;
        return super.add(obj);
    }

    // 当元素被删除时，我们将 delTimestamp 更新为当前时间，表示已经被删除。 这里只是标记删除，而非真正将它从容器中删除。
    @Override
    public boolean remove(Object obj) {
        for (int i = 0; i < totalSize; ++i) {
            if (super.get(i).equals(obj)) {
                delTimestamps[i] = System.currentTimeMillis();
                actualSize--;  // 没被删除标记的元素个数
            }
        }
        return true;
    }

    public int actualSize() {
        return this.actualSize;
    }

    public int totalSize() {
        return this.totalSize;
    }

    @Override
    public E get(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return super.get(i);
    }

    public long getAddTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return addTimestamps[i];
    }

    public long getDelTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return delTimestamps[i];
    }

    @Override
    public Iterator<E> iterator() {
        return new SnapshotArrayIterator<>(this);
    }

}
