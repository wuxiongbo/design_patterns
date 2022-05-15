package chapter61.v2;

import chapter61.v2.strategy.ISortAlg;
import chapter61.v2.strategy.concrete.ConcurrentExternalSort;
import chapter61.v2.strategy.concrete.ExternalSort;
import chapter61.v2.strategy.concrete.MapReduceSort;
import chapter61.v2.strategy.concrete.QuickSort;

import java.io.File;

/**
 * <p>策略模式</p>
 *
 * 重构思路：
 *  只要掌握了我们之前讲过的设计原则和思想，针对上面的问题，即便我们想不到该用什么 设计模式 来重构，也应该能知道该如何解决，
 *  那就是，将 Sorter 类中的某些代码拆分出来，独立成职责更加单一的小类。
 *  实际上，拆分 是应对 ‘类’ 或者 ‘函数’ 代码过多、应对代码复杂性的一个常用手段。
 *  按照这个解决思路，我们对代码进行重构。
 *  重构之后的代码如下所示：
 *
 * 重构后的亮点：
 *  经过拆分之后，每个类的代码都不会太多，每个类的逻辑都不会太复杂，代码的可读性、可维护性提高了。
 *  除此之外，我们将  排序算法  设计成独立的类，跟具体的业务逻辑（代码中的 if-else 那部分逻辑） '解耦' ，也让 排序算法 能够 '复用'。
 *
 *
 *
 * 新优化思路：
 *  这一步，实际上就是策略模式的第一步，也就是将 策略的定义 分离出来。
 *  实际上，上面的代码还可以继续优化。
 *  每种排序类 都是 '无状态' 的，我们没必要在每次使用的时候，都重新创建一个新的对象。
 *  所以，我们可以使用 '工厂模式' 对 '对象的创建' 进行封装。
 *  按照这个思路，我们对代码进行重构。
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
            sortAlg = new QuickSort();
        } else if (fileSize < 10 * GB) { // [6GB, 10GB)
            sortAlg = new ExternalSort();
        } else if (fileSize < 100 * GB) { // [10GB, 100GB)
            sortAlg = new ConcurrentExternalSort();
        } else { // [100GB, ~)
            sortAlg = new MapReduceSort();
        }


        sortAlg.sort(filePath);
    }

}
