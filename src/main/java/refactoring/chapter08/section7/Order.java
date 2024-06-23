package refactoring.chapter08.section7;

/**
 * @author bear
 * @date 2024/6/16 下午3:17
 * @description
 */
public class Order {

    private Customer customer;

    public void setCustomer(Customer customer) {

        if (this.customer != null) {
            this.customer.removeOrder(this);
        }

        this.customer = customer;

        this.customer.addOrder(this);
    }

}
