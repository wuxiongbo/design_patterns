package big_talk_design_patten.chapter20.iterator2;

/**
 * @author Xander Wu
 * @date 2023/12/10 15:32
 */
public interface ListIterator {

    public boolean hasNext();       //如果此列表迭代器在向前遍历列表时具有更多元素，则返回true

    public Object next();           //返回列表中的下一个元素并前进光标位置

    public boolean hasPrevious();   //如果此列表迭代器在反向遍历列表时具有更多元素，则返回true

    public Object previous();       //返回列表中的上一个元素并向后移动光标位置

}
