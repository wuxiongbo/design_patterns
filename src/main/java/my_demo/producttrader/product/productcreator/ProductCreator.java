package my_demo.producttrader.product.productcreator;

import my_demo.producttrader.Specification;
import my_demo.producttrader.product.Product;

/**
 * @author bear
 */
public interface ProductCreator {
    /**
     * Create a product based on the specification
     * @param spec specification
     * @return product
     */
    Product create(Specification spec);
}