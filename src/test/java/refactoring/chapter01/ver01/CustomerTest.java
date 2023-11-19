package refactoring.chapter01.ver01;


import org.junit.jupiter.api.Test;

public class CustomerTest {

	@Test
	public void statement() {

		Customer customer = new Customer("John");

		String title = "Titanic";
		int priceCode = 2;
		Movie movie = new Movie(title, priceCode);

		int _daysRented = 7;
		Rental rental = new Rental(movie, _daysRented);

		customer.addRental(rental);
		String result = customer.statement();

		System.out.println(result);
	}
}
