package design_patterns.chapter47.v2;

import design_patterns.chapter47.dependence.SearchWord;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>原型模式</p>
 *
 * 要求1，
 *    对于前面那个需求，我们有一个特殊的要求， 任何时刻，系统 A 中的所有数据都必须是同一个版本的，即：
 *      要么都是版本 a，
 *      要么都是版本 b，
 *      不能有的是版本 a，有的是版本 b。
 *
 *
 *    v1 的更新方式，就不能满足这个要求了。
 *
 *
 * 要求2，
 *    除此之外，我们还要求，在更新内存数据的时候，系统 A 不能处于  不可用状态，也就是  不能 停机更新数据。
 *
 *
 * 把 ‘正在使用’的数据的版本  定义为“服务版本”，当我们要更新内存中的数据的时候，
 * 我们并不是直接在 ‘服务版本’（假设是 版本 a 数据）上更新，而是  重新创建另一个版本数据（假设是 版本 b 数据），
 * 等 新的版本数据 建好之后，再一次性地将 服务版本 从  ‘版本 a’ 切换到 ‘版本 b’。
 *
 * 这样，既保证了数据一直可用，又避免了中间状态的存在。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */
public class Demo {
    //  服务版本数据
    private HashMap<String, SearchWord> currentKeywords = new HashMap<>();

    public void refresh() {

        // 新创建版本数据
        HashMap<String, SearchWord> newKeywords = new LinkedHashMap<>();

        // 从  数据库  中取出 “所有的新数据” ，放入到newKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords();

        for (SearchWord searchWord : toBeUpdatedSearchWords) {
            newKeywords.put(searchWord.getKeyword(), searchWord);
        }



        // 获取全部最新数据后，再切换 内存数据 版本
        currentKeywords = newKeywords;


        // 缺点：newKeywords 构建的成本比较高。
        // 我们需要将这 10 万条数据从数据库中读出，然后计算哈希值，构建 newKeywords。
        // 这个过程显然是比较耗时。为了提高效率，原型模式就派上用场了。

    }

    private List<SearchWord> getSearchWords() {
        // .... 从数据库中取出所有的数据
        return null;
    }
}
