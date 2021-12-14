package chapter47.v1;

import chapter47.dependence.SearchWord;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>原型模式 的引入</p>
 *
 * 如果对象的 创建成本 比较大，而  同一个类 的 不同实例对象 之间差别不大（大部分字段都相同），
 * 在这种情况下，我们可以利用对  “已有对象”（原型对象）  进行  复制（或者叫拷贝）的方式 来创建 “新对象” ，以达到节省创建时间的目的。
 * 这种基于  “原型”  来创建 “对象” 的方式 就叫作 原型设计模式（Prototype Design Pattern），简称 原型模式。
 *
 * 需求：
 *      为了保证  ‘系统 A’  中，数据的实时性（不一定非常实时，但数据也不能太旧），
 *      ‘系统 A’ 需要 定期 根据 ‘数据库’ 中的数据，更新 内存中的 索引 和 数据。
 *
 *
 * 实现：
 *      在系统 A 中，记录  当前数据的版本 Va 对应的更新时间 Ta，从数据库中捞出更新时间大于 Ta 的所有搜索关键词，
 *      也就是找出 Va 版本 与 最新版本数据的“差集”，然后针对差集中的每个关键词进行处理。
 *
 * 如果它 已经在散列表中 存在了，我们就更新相应的搜索次数、更新时间等信息；
 * 如果它 在散列表中 不存在，我们就将它插入到散列表中。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */
public class Demo {

    // 散列表。   代表 内存中的 索引 和 数据
    private ConcurrentHashMap<String, SearchWord> currentKeywords = new ConcurrentHashMap<>();

    // 最后更新时间
    private long lastUpdateTime = -1;


    public void refresh() {

        // 从  数据库  中取出  “更新时间 > lastUpdateTime 的数据” ，放入到currentKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);

        long maxNewUpdatedTime = lastUpdateTime;



        for (SearchWord searchWord : toBeUpdatedSearchWords) {

            // 循环时，找出最大时间戳。
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }


            // 内存中 存在， 则 更新 内存数据
            if (currentKeywords.containsKey(searchWord.getKeyword())) {
                currentKeywords.replace(searchWord.getKeyword(), searchWord);
            }
            // 内存中 不存在，则 插入 到内存数据
            else {
                currentKeywords.put(searchWord.getKeyword(), searchWord);
            }


            // 弊端： currentKeywords 存在 新旧版本并存 的 中间态。

        }

        // 跳出循环后，再把时间戳 赋值给 变量 lastUpdateTime，方便下一个获取数据
        lastUpdateTime = maxNewUpdatedTime;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // ... 从数据库中取出  更新时间 > lastUpdateTime  的数据
        return null;
    }
}
