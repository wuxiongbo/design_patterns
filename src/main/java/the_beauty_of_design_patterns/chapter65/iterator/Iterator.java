package the_beauty_of_design_patterns.chapter65.iterator;

/**
 * <p>迭代器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public interface Iterator<E> {

    /**
     * 判断是否存在下一个元素
     * @return
     */
    boolean hasNext();

    /**
     * 移动游标
     */
    void next();

    /**
     * 获取当前元素
     * @return
     */
    E currentItem();

}