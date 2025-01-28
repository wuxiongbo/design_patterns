package my_demo.producttrader;

import my_demo.producttrader.product.Product;
import my_demo.producttrader.product.productcreator.ProductCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * 操盘手
 * <p>
 *
 * 1）记录映射配置机制。
 * 接收一个 ConcreteProduct 对应的 Specification。
 * 映射 Specification 和 ProductCreator。
 * 2）提供映射配置机制。
 * 调用 ProductCreator 以生成符合 Specification 的 ConcreteProduct。
 *
 * @author bear
 */
public class ProductTrader {

    /**
     * 字典对象:
     * 用于存储 Specification 和 ProductCreator 的映射关系
     */
    private final Map<Specification, ProductCreator> dict = new HashMap<>();

    /**
     * 生产指定规格的产品
     * @param spec
     * @return
     */
    public Product createFor(Specification spec) {
        ProductCreator creator = lookupCreator(spec);
        return creator.create(spec);
    }

    /**
     * 查找指定规格的产品工厂
     * @param spec
     * @return
     */
    public ProductCreator lookupCreator(Specification spec) {
        return dict.get(spec);
    }

    /**
     * 增加映射配置
     * @param spec
     * @param creator
     */
    public void addCreator(Specification spec, ProductCreator creator) {
        dict.put(spec, creator);
    }

    /**
     * 移除映射配置
     * @param spec
     */
    public void removeCreator(Specification spec) {
        dict.remove(spec);
    }

    /**
     * 替换映射配置
     * @param spec
     * @param creator
     */
    public void substituteCreator(Specification spec, ProductCreator creator) {
        dict.put(spec, creator);
    }
}