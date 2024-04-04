package my_demo.producttrader;

import my_demo.producttrader.product.Product;
import my_demo.producttrader.product.productcreator.ConcreteProductCreator;
import my_demo.producttrader.product.productcreator.ProductCreator;
import org.junit.Test;

/**
 * @author bear
 */
public class Client {


    @Test
    public void testCase1() {

        Specification spec1 = new Specification();
        spec1.setCriteria(ConcreteProductCreator.FRO_PRODUCT_A);

        Specification spec2 = new Specification();
        spec2.setCriteria(ConcreteProductCreator.FRO_PRODUCT_B);

        ProductCreator creator = new ConcreteProductCreator();


        ProductTrader trader = new ProductTrader();
        trader.addCreator(spec1, creator);
        trader.addCreator(spec2, creator);

        Specification spec3 = new Specification();
        spec3.setCriteria(ConcreteProductCreator.FRO_PRODUCT_A);

        Product product = trader.createFor(spec3);
        System.out.println(product.getCriteria());
    }
}