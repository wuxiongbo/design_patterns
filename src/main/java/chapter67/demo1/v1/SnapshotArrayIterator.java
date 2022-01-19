package chapter67.demo1.v1;

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

    // 迭代器创建时间戳 snapshotTimestamp
    private long snapshotTimestamp;

    private int cursorInAll; // 在整个容器中的 游标，而非快照中的下标

    private int leftCount;  // 快照中还有几个元素未被遍历

    private MyArrayList<E> arrayList;

    public SnapshotArrayIterator(MyArrayList<E> arrayList) {
        this.snapshotTimestamp = System.currentTimeMillis();
        this.cursorInAll = 0;
        this.leftCount = arrayList.actualSize();   // 没被删除标记的元素个数
        this.arrayList = arrayList;

        justNext(); // 先跳到这个迭代器快照的第一个元素
    }

    @Override
    public boolean hasNext() {
        return this.leftCount > 0;
    }

    @Override
    public E next() {
        E currentItem = arrayList.get(cursorInAll);
        justNext();
        return currentItem;
    }

    private void justNext() {

        // 容器游标、 容器元素个数
        while (cursorInAll < arrayList.totalSize()) {

            // 获取当前元素 的 添加时间、删除时间
            long addTimestamp = arrayList.getAddTimestamp(cursorInAll);
            long delTimestamp = arrayList.getDelTimestamp(cursorInAll);


            // 元素属于  快照迭代器：
            //    addTimestamp < snapshotTimestamp < delTimestamp
            //    在快照 前新增 且 在快照 后 删除/未删除

            // 元素不属于  快照迭代器：
            //    addTimestamp > snapshotTimestamp
            //    在快照 后 新增

            //    delTimestamp < snapshotTimestamp
            //    在快照 前 删除

            // 如果当前元素属于当前迭代器
            if (snapshotTimestamp > addTimestamp && snapshotTimestamp < delTimestamp) {

                // 则将 快照迭代器 中， 未遍历元素的个数 -1。
                leftCount--;
                break;

            }

            // 容器游标
            cursorInAll++;

        }

    }
}