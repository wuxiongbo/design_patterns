package refactoring.chapter01.ver11.price;

public interface Price {
	int getPriceCode(); // 取得价格代号

	double getCharge(int daysRented);

//	public int getFrequentRenterPoints(int daysRented) {
//		if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
//			return 2;
//		else
//			return 1;
//	}
	
	default int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}
