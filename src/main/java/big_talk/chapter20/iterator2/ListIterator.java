package big_talk.chapter20.iterator2;

/**
 * @author Xander Wu
 * @date 2023/12/10 15:32
 */
public interface ListIterator {

    // 如果此列表迭代器在向前遍历列表时具有更多元素，则返回true
    boolean hasNext();

    // 返回列表中的下一个元素并前进光标位置
    Object next();

    // 如果此列表迭代器在反向遍历列表时具有更多元素，则返回true
    boolean hasPrevious();

    // 返回列表中的上一个元素并向后移动光标位置
    Object previous();

}
