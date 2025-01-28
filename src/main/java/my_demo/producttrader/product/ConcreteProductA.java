package my_demo.producttrader.product;

import static my_demo.producttrader.product.productcreator.ConcreteProductCreator.FRO_PRODUCT_A;

/**
 * 具体的产品A
 * @author bear
 */
public class ConcreteProductA implements Product {

    @Override
    public String getCriteria() {
        return FRO_PRODUCT_A;
    }

}