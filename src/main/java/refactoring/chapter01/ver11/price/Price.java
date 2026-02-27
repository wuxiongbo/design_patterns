package refactoring.chapter01.ver11.price;

public interface Price {

	// 取得价格代号
	int getPriceCode();

	double getCharge(int daysRented);

	default int getFrequentRenterPoints(int daysRented){
        return 1;
    }

}
