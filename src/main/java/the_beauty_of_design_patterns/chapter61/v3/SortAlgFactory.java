package the_beauty_of_design_patterns.chapter61.v3;

import the_beauty_of_design_patterns.chapter61.v2.strategy.ISortAlg;
import the_beauty_of_design_patterns.chapter61.v2.strategy.concrete.ConcurrentExternalSort;
import the_beauty_of_design_patterns.chapter61.v2.strategy.concrete.ExternalSort;
import the_beauty_of_design_patterns.chapter61.v2.strategy.concrete.MapReduceSort;
import the_beauty_of_design_patterns.chapter61.v2.strategy.concrete.QuickSort;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 策略工厂 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class SortAlgFactory {
    private static final Map<String, ISortAlg> algs = new HashMap<>();

    // 后续只需修改 静态代码段， 达到代码改动最小化、集中化
    static {
        algs.put("QuickSort", new QuickSort());
        algs.put("ExternalSort", new ExternalSort());
        algs.put("ConcurrentExternalSort", new ConcurrentExternalSort());
        algs.put("MapReduceSort", new MapReduceSort());
    }

    public static ISortAlg getSortAlg(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return algs.get(type);
    }
}
