package big_talk.chapter7.proxy2;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		SchoolGirl girlLjj = new SchoolGirl();
		girlLjj.setName("李娇娇");

		Proxy boyDl = new Proxy(girlLjj);
		boyDl.giveDolls(); 
		boyDl.giveFlowers();
		boyDl.giveChocolate();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//代理类

