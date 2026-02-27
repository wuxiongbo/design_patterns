package big_talk.chapter20.iterator2;

/**
 * @author Xander Wu
 * @date 2023/12/10 15:32
 */
public interface Iterator {

    // 如果迭代具有更多元素，则返回true
    boolean hasNext();

    // 返回迭代中的下一个元素
    Object next();

}
