package chapter67.v1;


import java.lang.reflect.Field;
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

    private int actualSize; // 真实元素个数。   actual 真实的

    // 添加元素的 时间戳
    private long[] addTimestamps;
    // 删除元素的 时间戳
    private long[] delTimestamps;

    public MyArrayList() {
        super();

        // 忽略扩容问题
        this.addTimestamps = new long[DEFAULT_CAPACITY];
        this.delTimestamps = new long[DEFAULT_CAPACITY];

        this.actualSize = 0;
    }

    // 当元素被加入到集合中的时候，我们将 addTimestamp 设置为当前时间，将 delTimestamp 设置成最大长整型值（Long.MAX_VALUE）。
    @Override
    public boolean add(E obj) {
        addTimestamps[size()] = System.currentTimeMillis();
        delTimestamps[size()] = Long.MAX_VALUE;

        actualSize++;
        return super.add(obj);
    }

    // 当元素被删除时，我们将 delTimestamp 更新为当前时间，表示已经被删除。
    // 这里只是标记删除，而非真正将它从容器中删除。
    @Override
    public boolean remove(Object obj) {
        for (int i = 0; i < size(); ++i) {
            if (super.get(i).equals(obj)
                    // 防重复删除
                    && delTimestamps[i] == Long.MAX_VALUE) {

                delTimestamps[i] = System.currentTimeMillis();
                actualSize--;  // 真实元素个数 -1

            }
        }
        return true;
    }

    public int actualSize() {
        return this.actualSize;
    }


    @Override
    public E get(int i) {
        if (i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return super.get(i);
    }

    public long getAddTimestamp(int i) {
        if (i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return addTimestamps[i];
    }

    public long getDelTimestamp(int i) {
        if (i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return delTimestamps[i];
    }

    @Override
    public Iterator<E> iterator() {
        return new SnapshotArrayIterator<>(this);
    }


    public int getArrayListCapacity() {
        try {

            Class<?> superclass = this.getClass().getSuperclass();

            //获取 elementData 字段
            Field field = superclass.getDeclaredField("elementData");
            field.setAccessible(true);
            //把示例传入get，获取实例字段elementData的值
            Object[] objects = (Object[])field.get(this);
            return objects.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
