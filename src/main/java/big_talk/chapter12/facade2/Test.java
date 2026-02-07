package big_talk.chapter12.facade2;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        Fund fund1 = new Fund();
        //基金购买
        fund1.buyFund();
        //基金赎回
        fund1.sellFund();


		System.out.println();
		System.out.println("**********************************************");

	}
}

//基金类

