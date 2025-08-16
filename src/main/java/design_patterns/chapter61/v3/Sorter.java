package design_patterns.chapter61.v3;

import design_patterns.chapter61.v2.strategy.ISortAlg;

import java.io.File;

/**
 * <p>策略模式</p>
 *
 * new操作交给 策略工厂 完成，使用 策略 的时候不用手动的new了
 *
 *
 * 经过上面两次重构之后，现在的代码实际上已经符合策略模式的代码结构了。
 * 我们通过 ‘策略模式’ 将 策略的 定义、创建、使用  解耦，让每一部分都不至于太复杂。
 *
 *
 *
 * 不过，Sorter 类中的 sortFile() 函数还是有一堆 if-else 逻辑。
 * 这里的 if-else 逻辑分支不多、也不复杂，这样写完全没问题。
 * 但如果你特别想将 if-else 分支判断移除掉，那也是有办法的。
 * 我直接给出代码，你一看就能明白。
 * 实际上，这也是基于 查表法 来解决的，其中的“algs”就是“表”
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class Sorter {
    private static final long GB = 1000 * 1000 * 1000;

    public void sortFile(String filePath) {

        // 省略校验逻辑

        File file = new File(filePath);
        long fileSize = file.length();


        ISortAlg sortAlg;


        if (fileSize < 6 * GB) { // [0, 6GB)
            sortAlg = SortAlgFactory.getSortAlg("QuickSort");
        } else if (fileSize < 10 * GB) { // [6GB, 10GB)
            sortAlg = SortAlgFactory.getSortAlg("ExternalSort");
        } else if (fileSize < 100 * GB) { // [10GB, 100GB)
            sortAlg = SortAlgFactory.getSortAlg("ConcurrentExternalSort");
        } else { // [100GB, ~)
            sortAlg = SortAlgFactory.getSortAlg("MapReduceSort");
        }


        sortAlg.sort(filePath);
    }
}
