package refactoring.chapter01.ver11.price;

public interface Price {

	int getPriceCode(); // 取得价格代号

	double getCharge(int daysRented);

	default int getFrequentRenterPoints(int daysRented){
        return 1;
    }

}
