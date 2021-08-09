package chapter47.v1;

import chapter47.dependence.SearchWord;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>描述类的信息</p>
 *
 * 为了保证系统 A 中数据的实时性（不一定非常实时，但数据也不能太旧），系统 A 需要定期根据数据库中的数据，更新内存中的索引和数据。
 *
 *
 * 实现：
 * 在系统 A 中，记录当前数据的版本 Va 对应的更新时间 Ta，从数据库中捞出更新时间大于 Ta 的所有搜索关键词，
 * 也就是找出 Va 版本与最新版本数据的“差集”，然后针对差集中的每个关键词进行处理。
 *
 * 如果它已经在散列表中存在了，我们就更新相应的搜索次数、更新时间等信息；
 * 如果它在散列表中不存在，我们就将它插入到散列表中。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */
public class Demo {

    private ConcurrentHashMap<String, SearchWord> currentKeywords = new ConcurrentHashMap<>();

    // 最后更新时间
    private long lastUpdateTime = -1;

    public void refresh() {
        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到currentKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);

        long maxNewUpdatedTime = lastUpdateTime;

        for (SearchWord searchWord : toBeUpdatedSearchWords) {


            // 循环时,找出最大时间戳，跳出循环后，再把时间戳 赋值给变量，方便下一个获取数据
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }


            // 已经在散列表中存在了，更新
            if (currentKeywords.containsKey(searchWord.getKeyword())) {
                currentKeywords.replace(searchWord.getKeyword(), searchWord);
            }
            // 散列表中不存在，将它插入到散列表中
            else {
                currentKeywords.put(searchWord.getKeyword(), searchWord);
            }

            // 弊端： currentKeywords 存在新旧版本并存的中间态。

        }

        lastUpdateTime = maxNewUpdatedTime;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // TODO: 从数据库中取出更新时间>lastUpdateTime的数据
        return null;
    }
}
