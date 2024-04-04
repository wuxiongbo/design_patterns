package my_demo.producttrader;

import my_demo.producttrader.product.Product;
import my_demo.producttrader.product.productcreator.ProductCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * 操盘手
 * <p>
 * 从 Client 接收一个 ConcreteProduct 对应的 Specification。
 * 映射 Specification 和 Creator。
 * 提供映射配置机制。
 * 调用 Creator 以生成符合 Specification 的 ConcreteProduct。
 *
 * @author bear
 */
public class ProductTrader {

    /**
     * 字典对象:
     * 用于存储 Specification 和 ProductCreator 的映射关系
     */
    private final Map<Specification, ProductCreator> dict = new HashMap<>();

    public Product createFor(Specification spec) {
        ProductCreator creator = lookupCreator(spec);
        return creator.create(spec);
    }

    public ProductCreator lookupCreator(Specification spec) {
        return dict.get(spec);
    }

    public void addCreator(Specification spec, ProductCreator creator) {
        dict.put(spec, creator);
    }

    public void removeCreator(Specification spec) {
        dict.remove(spec);
    }

    public void substituteCreator(Specification spec, ProductCreator creator) {
        dict.put(spec, creator);
    }
}