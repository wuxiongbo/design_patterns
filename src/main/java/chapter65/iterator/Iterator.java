package chapter65.iterator;

/**
 * <p>迭代器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public interface Iterator<E> {

    boolean hasNext();

    void next();

    E currentItem();

}