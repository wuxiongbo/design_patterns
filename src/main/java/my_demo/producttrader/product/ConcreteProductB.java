package my_demo.producttrader.product;

import static my_demo.producttrader.product.productcreator.ConcreteProductCreator.FRO_PRODUCT_B;

/**
 * 具体的产品B
 */
public class ConcreteProductB implements Product {

    @Override
    public String getCriteria() {
        return FRO_PRODUCT_B;
    }

}