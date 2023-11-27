package refactoring.chapter01.ver10.price;

public interface Price {
	int getPriceCode(); // 取得价格代号

	double getCharge(int daysRented);

}
