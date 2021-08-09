package chapter47.v3;

import chapter47.dependence.SearchWord;

import java.util.HashMap;
import java.util.List;

/**
 * <p>浅拷贝</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */

public class Demo {

    private HashMap<String, SearchWord> currentKeywords = new HashMap<>();

    private long lastUpdateTime = -1;

    public void refresh() {

        // Shallow copy
        // 原型模式就这么简单，拷贝已有对象的数据，更新少量差值。  此处还存在一个问题。HashMap 上的 clone() 为浅拷贝。
        HashMap<String, SearchWord> newKeywords = (HashMap<String, SearchWord>) currentKeywords.clone();

        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeywords中。   只捞出新增或者有更新的关键词，更新到 newKeywords 中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);

        long maxNewUpdatedTime = lastUpdateTime;

        for (SearchWord searchWord : toBeUpdatedSearchWords) {

            // 循环时,找出最大时间戳，跳出循环后，再把时间戳 赋值给变量，方便下一个获取数据
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }

            // 方案一：对克隆的map 进行更新、插入操作。
            // 对已存在的进行更新。
//            if (newKeywords.containsKey(searchWord.getKeyword())) {
//                SearchWord oldSearchWord = newKeywords.get(searchWord.getKeyword());
//                oldSearchWord.setCount(searchWord.getCount());
//                oldSearchWord.setLastUpdateTime(searchWord.getLastUpdateTime());
//            }
            //对不存在的，进行插入。
//            else {
//                newKeywords.put(searchWord.getKeyword(), searchWord);
//            }
            // 缺点： currentKeywords  存在介于 老版本 与 新版本 之间的 中间状态。


            // 方案二：同样，操作克隆的map。
            // 对已存在的进行移除。  这样，就不会修改到 currentKeywords 中的数据
            if (newKeywords.containsKey(searchWord.getKeyword())) {
                newKeywords.remove(searchWord.getKeyword());
            }
            // 然后进行插入。  避免更新造成 中间态。
            newKeywords.put(searchWord.getKeyword(), searchWord);

        }

        lastUpdateTime = maxNewUpdatedTime;

        // 完成更新操作后，进行版本切换
        currentKeywords = newKeywords;

    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // TODO: 从数据库中取出更新时间>lastUpdateTime的数据
        return null;
    }
}