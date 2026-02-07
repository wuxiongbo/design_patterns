package big_talk.chapter7.proxy1;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		SchoolGirl girlLjj = new SchoolGirl();
		girlLjj.setName("李娇娇");

		Pursuit boyZjy = new Pursuit(girlLjj);
		boyZjy.giveDolls(); 
		boyZjy.giveFlowers();
		boyZjy.giveChocolate();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//追求者类

