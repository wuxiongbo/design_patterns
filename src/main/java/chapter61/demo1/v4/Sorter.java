package chapter61.demo1.v4;

import chapter61.demo1.v2.strategy.ISortAlg;
import chapter61.demo1.v3.SortAlgFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 *
 * 现在的代码实现就更加优美了。
 * 我们把 可变的部分 隔离到了 策略工厂类 和 Sorter 类 中的 静态代码段中。
 *
 * 当要添加一个新的排序算法时，我们只需要修改 策略工厂类 和 Sort 类 中的 静态代码段，其他代码都不需要修改，这样就将代码改动最小化、集中化了。
 *
 *
 * 你可能会说，即便这样，当我们添加新的排序算法的时候，还是需要修改代码，并不完全符合‘开闭原则’。有什么办法让我们完全满足开闭原则呢？
 *
 * 对于 Java 语言来说，我们可以通过 ‘反射’ 来避免对策略工厂类的修改。
 *
 * 具体是这么做的：
 *      1）我们通过一个配置文件或者自定义的 annotation 来标注都有哪些策略类；
 *      2）策略工厂类 读取 配置文件 或者 搜索被 annotation 标注的策略类，
 *      3）然后通过 反射 动态地  加载策略类、创建策略对象；
 *      4）当我们新添加一个策略的时候，只需要 将 新增的策略类  添加到配置文件  或者  用 annotation 标注即可。
 *
 * 还记得上一节课的课堂讨论题吗？我们也可以用这种方法来解决。
 *
 * 对于 Sorter 来说，我们可以通过同样的方法来避免修改。
 * 我们通过将 ‘文件大小区间’ 和 ‘算法’ 之间的 对应关系 放到 配置文件 中。
 * 当添加 新的排序算法 时，我们只需要改动 配置文件 即可，不需要改动代码。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class Sorter {
    private static final long GB = 1000 * 1000 * 1000;

    // “algs”就是“表”。 将 ‘策略’ 初始化到 ‘表’ 中。
    private static final List<AlgRange> algs = new ArrayList<>();


    // 后续只需修改 静态代码段， 达到代码改动最小化、集中化
    static {
        algs.add(new AlgRange(0, 6*GB, SortAlgFactory.getSortAlg("QuickSort")));
        algs.add(new AlgRange(6*GB, 10*GB, SortAlgFactory.getSortAlg("ExternalSort")));
        algs.add(new AlgRange(10*GB, 100*GB, SortAlgFactory.getSortAlg("ConcurrentExternalSort")));
        algs.add(new AlgRange(100*GB, Long.MAX_VALUE, SortAlgFactory.getSortAlg("MapReduceSort")));
    }


    public void sortFile(String filePath) {

        // 省略校验逻辑

        File file = new File(filePath);
        long fileSize = file.length();


        ISortAlg sortAlg = null;

        // 通过 ‘查表法’  去掉 if else
        for (AlgRange algRange : algs) {
            if (algRange.inRange(fileSize)) {
                sortAlg = algRange.getAlg();
                break;
            }
        }


        sortAlg.sort(filePath);
    }


    private static class AlgRange {

        // 文件大小的区间范围
        private long start;
        private long end;

        // 策略
        private ISortAlg alg;

        public AlgRange(long start, long end, ISortAlg alg) {
            this.start = start;
            this.end = end;
            this.alg = alg;
        }

        // 获取策略
        public ISortAlg getAlg() {
            return alg;
        }

        // 范围判断
        public boolean inRange(long size) {
            return size >= start && size < end;
        }
    }

}
