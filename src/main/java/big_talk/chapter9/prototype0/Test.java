package big_talk.chapter9.prototype0;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		ConcretePrototype p1 = new ConcretePrototype("编号123456");
		System.out.println("原ID:"+ p1.getID());

		ConcretePrototype c1 = (ConcretePrototype)p1.clone();
		System.out.println("克隆ID:"+ c1.getID());

		System.out.println();
		System.out.println("**********************************************");

	}
}

//原型类

