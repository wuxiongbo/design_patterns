package big_talk.chapter14.observer3;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        //老板胡汉三
        Subject boss1 = new Boss("胡汉三");

        //看股票的同事
        Observer employee1 = new StockObserver("魏关姹",boss1);
        Observer employee2 = new StockObserver("易管查",boss1);
        //看NBA的同事
        Observer employee3 = new NBAObserver("兰秋幂",boss1);

        //老板登记下三个同事
        boss1.attach(employee1);
        boss1.attach(employee2);
        boss1.attach(employee3);

        boss1.detach(employee1); //魏关姹其实没有被通知到，所有减去

        //老板回来
        boss1.setAction("我胡汉三回来了");
        //通知两个同事
        boss1.notifyEmployee();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//通知者接口

