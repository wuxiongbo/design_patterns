package refactoring.chapter01.ver11;


import org.junit.jupiter.api.Test;

public class CustomerTest {

	@Test
	public void statement() {
		Customer customer = new Customer("John");
		String title = "Titanic";
		// 价格代号
		int priceCode = 1;
		// 租期
		int _daysRented = 7;
		Movie movie = new Movie(title, priceCode);
		Rental rental = new Rental(movie, _daysRented);
		customer.addRental(rental);
		String result = customer.statement();
		System.out.println(result);
	}
}
