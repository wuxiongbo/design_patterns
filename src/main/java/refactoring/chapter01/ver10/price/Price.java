package refactoring.chapter01.ver10.price;

public interface Price {

	int getPriceCode(); // 取得价格代号

	/**
	 * 处理完所有 case分支 之后，把 Price.getCharge() 声明为 abstract
	 * @param daysRented 租赁天数
	 * @return 价格
	 */
	double getCharge(int daysRented);

}
