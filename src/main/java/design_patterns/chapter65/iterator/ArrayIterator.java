package design_patterns.chapter65.iterator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * <p> 自定义迭代器实现 </p>
 *
 * role: ConcreteIterator
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class ArrayIterator<E> implements Iterator<E> {
    // 游标
    private int cursor;


    // 待遍历的对象 与  迭代器。 是 单向依赖关系
    private ArrayList<E> arrayList;

    // 依赖注入
    public ArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }


    @Override
    public boolean hasNext() {
        return cursor != arrayList.size();
        //注意：这里的 游标cursor，在指向最后一个元素的时候，hasNext()仍旧返回true。
    }

    // 移动 游标
    @Override
    public void next() {
//        cursor++;
    }

    // 获取当前元素
    @Override
    public E currentItem() {
        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }
        E e = arrayList.get(cursor);
        cursor++; // 移动 游标
        return e;
    }
}
