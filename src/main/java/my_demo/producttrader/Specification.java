package my_demo.producttrader;

import lombok.Getter;
import lombok.Setter;
import my_demo.producttrader.product.Product;

import java.util.Objects;

/**
 * 产品规格
 * <p>
 * 一个 Specification 代表着一个 ConcreteProduct 类。
 * 作为映射和查询 Creator 的条件参数
 *
 * @author bear
 */
@Setter
@Getter
public class Specification {

    private String criteria;

    /**
     * 判断产品是否符合规格
     *
     * @param product 产品
     * @return 是否符合规格
     */
    public boolean isSatisfiedBy(Product product) {
        return Objects.equals(product.getCriteria(), this.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criteria);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Specification that = (Specification) obj;
        return Objects.equals(criteria, that.criteria);
    }

}