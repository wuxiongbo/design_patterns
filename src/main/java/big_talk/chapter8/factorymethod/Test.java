package big_talk.chapter8.factorymethod;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		Creator[] creators = new Creator[2];
		creators[0] = new ConcreteCreatorA();
		creators[1] = new ConcreteCreatorB();

		for(Creator item : creators){
			Product product = item.factoryMethod();
			product.make();
		}

		System.out.println();
		System.out.println("**********************************************");

	}
}

//Product类

