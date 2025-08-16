package design_patterns.chapter47.v4;

import design_patterns.chapter47.dependence.SearchWord;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * <p>  原型模式 的实现方式： 深拷贝 </p>
 *
 * 原型模式有两种实现方法，深拷贝和浅拷贝。
 *
 * 浅拷贝 只会复制对象中基本数据类型数据和引用对象的内存地址，不会递归地复制引用对象，以及引用对象的引用对象……
 * 而 深拷贝 得到的是一份完完全全独立的对象。
 * 所以，深拷贝比起浅拷贝来说，更加耗时，更加耗内存空间。
 *
 *
 * 对于可变对象来说，  浅拷贝  得到的对象和原始对象会共享部分数据，就有可能出现数据被修改的风险.
 *
 * 一般情况，推荐使用深拷贝。
 * 没有充分的理由，不要为了一点点的性能提升而使用 浅拷贝。
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

        // 深拷贝
        HashMap<String, SearchWord> newKeywords = new HashMap<>();
        for (HashMap.Entry<String, SearchWord> entry : currentKeywords.entrySet()) {
            SearchWord searchWord = entry.getValue();

            // 深拷贝
            SearchWord newSearchWord = deepCopy1(searchWord);

            newKeywords.put(entry.getKey(), newSearchWord);
        }


        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWord searchWord : toBeUpdatedSearchWords) {

            // 更新最大时间
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }

            // 更新 由深拷贝得到的map （newKeywords）
            // 对 map内存中 已存在的进行更新。（这部分是深拷贝）
            if (newKeywords.containsKey(searchWord.getKeyword())) {
                SearchWord oldSearchWord = newKeywords.get(searchWord.getKeyword());
                oldSearchWord.setCount(searchWord.getCount());
                oldSearchWord.setLastUpdateTime(searchWord.getLastUpdateTime());
            }
            // 对 内存中 不存在的，进行插入。（这部分是浅拷贝）
            else {
                newKeywords.put(searchWord.getKeyword(), searchWord);
            }

        }


        lastUpdateTime = maxNewUpdatedTime;

        // 版本切换
        currentKeywords = newKeywords;
    }


    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // ...从数据库中取出更新时间>lastUpdateTime的数据
        return List.of();
    }

    /**
     * 深拷贝，第一种方法：创建新对象
     * @param searchWord
     * @return cloneObject
     */
    public SearchWord deepCopy1(SearchWord searchWord) {
        return new SearchWord(searchWord.getKeyword(), searchWord.getCount(), searchWord.getLastUpdateTime());
    }


    /**
     * 深拷贝，第二种方法：先将对象序列化，然后再反序列化成新的对象。
     * @param originalObject
     * @return cloneObject
     */
    public <T> T deepCopy2(T originalObject) {
        T cloneObject;
        try {
            // 序列化
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(originalObject);

            // 反序列化
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            cloneObject = (T)oi.readObject();

            return cloneObject;

        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("111");
            System.err.println(e);
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("222");
            System.err.println(e);
        }

        return null;
    }




}
