package big_talk.chapter12.facade1;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        Stock1 stock1 = new Stock1();
        Stock2 stock2 = new Stock2();
        NationalDebt1 nd1 = new NationalDebt1();
        Realty1 rt1 = new Realty1();

        stock1.buy();
        stock2.buy();
        nd1.buy();
        rt1.buy();
        
        stock1.sell();
        stock2.sell();
        nd1.sell();
        rt1.sell();


		System.out.println();
		System.out.println("**********************************************");

	}
}

//股票1

