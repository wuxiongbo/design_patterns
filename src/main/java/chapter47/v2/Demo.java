package chapter47.v2;

import chapter47.dependence.SearchWord;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * 我们有一个特殊的要求：
 *    任何时刻，系统 A 中的所有数据都必须是同一个版本的，
 *    要么都是版本 a，
 *    要么都是版本 b，
 *    不能有的是版本 a，有的是版本 b。
 * 那刚刚的更新方式就不能满足这个要求了。
 *
 * 除此之外，我们还要求：
 *    在更新内存数据的时候，系统 A 不能处于不可用状态，也就是不能停机更新数据。
 *
 *
 *
 * 把正在使用的数据的版本定义为“服务版本”，
 * 当我们要更新内存中的数据的时候，我们并不是直接在服务版本（假设是版本 a 数据）上更新，而是重新创建另一个版本数据（假设是版本 b 数据），
 * 等新的版本数据建好之后，再一次性地将服务版本从版本 a 切换到版本 b。
 *
 * 这样既保证了数据一直可用，又避免了中间状态的存在。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */
public class Demo {
    private HashMap<String, SearchWord> currentKeywords=new HashMap<>();

    public void refresh() {

        HashMap<String, SearchWord> newKeywords = new LinkedHashMap<>();

        // 从数据库中取出 “所有的数据” ，放入到newKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords();

        for (SearchWord searchWord : toBeUpdatedSearchWords) {
            newKeywords.put(searchWord.getKeyword(), searchWord);
        }

        // 获取全部数据后，再切换版本
        currentKeywords = newKeywords;


        // 缺点：newKeywords 构建的成本比较高。
        // 我们需要将这 10 万条数据从数据库中读出，然后计算哈希值，构建 newKeywords。
        // 这个过程显然是比较耗时。为了提高效率，原型模式就派上用场了。

    }

    private List<SearchWord> getSearchWords() {
        // TODO: 从数据库中取出所有的数据
        return null;
    }
}
