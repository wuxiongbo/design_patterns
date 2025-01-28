package my_demo.producttrader;

import my_demo.producttrader.product.Product;
import my_demo.producttrader.product.productcreator.ConcreteProductCreator;
import my_demo.producttrader.product.productcreator.ProductCreator;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * 操盘手模式
 * @author bear
 */
public class Client {


    @Test
    public void operation() {

        ProductTrader trader = getProductTrader();

        // 规格1
        Specification spec1Copy = new Specification();
        spec1Copy.setCriteria(ConcreteProductCreator.FRO_PRODUCT_A);

        // 生产 产品A
        Product productA = trader.createFor(spec1Copy);

        System.out.println(productA.getCriteria());

    }

    @NotNull
    private static ProductTrader getProductTrader() {
        // 规格1
        Specification spec1 = new Specification();
        spec1.setCriteria(ConcreteProductCreator.FRO_PRODUCT_A);

        // 规格2
        Specification spec2 = new Specification();
        spec2.setCriteria(ConcreteProductCreator.FRO_PRODUCT_B);

        // 产品工厂
        ProductCreator creator = new ConcreteProductCreator();

        // 操盘手，记录  规格1、规格2 映射的 产品工厂
        ProductTrader trader = new ProductTrader();
        trader.addCreator(spec1, creator);
        trader.addCreator(spec2, creator);
        return trader;
    }
}