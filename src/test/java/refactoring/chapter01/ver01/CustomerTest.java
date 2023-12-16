package refactoring.chapter01.ver01;


import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void statement() {

        // 1 消费者
        Customer customer = new Customer("John");

        // 2 电影
        String title = "Titanic";
        int priceCode = 2;
        Movie movie = new Movie(title, priceCode);

        // 3 租赁信息
        int _daysRented = 7;
        Rental rental = new Rental(movie, _daysRented);

        // 消费者租赁
        customer.addRental(rental);

        // 拿到账单
        String result = customer.statement();

        System.out.println(result);
    }
}
