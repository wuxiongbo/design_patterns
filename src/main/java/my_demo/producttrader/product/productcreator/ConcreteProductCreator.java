package my_demo.producttrader.product.productcreator;

import my_demo.producttrader.Specification;
import my_demo.producttrader.product.ConcreteProductA;
import my_demo.producttrader.product.ConcreteProductB;
import my_demo.producttrader.product.Product;

/**
 * 产品工厂
 * @author bear
 */
public class ConcreteProductCreator implements ProductCreator {

    // 产品标准
    public final static String FRO_PRODUCT_A = "SpecForConreteProductA";
    public final static String FRO_PRODUCT_B = "SpecForConreteProductB";


    @Override
    public Product create(Specification spec) {

        if (FRO_PRODUCT_A.equals(spec.getCriteria())) {
            return new ConcreteProductA();
        } else if (FRO_PRODUCT_B.equals(spec.getCriteria())) {
            return new ConcreteProductB();
        }

        throw new UnsupportedOperationException();
    }

}