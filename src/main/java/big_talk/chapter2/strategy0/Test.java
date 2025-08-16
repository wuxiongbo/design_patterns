package big_talk.chapter2.strategy0;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		Context context;

		//由于实例化不同的策略，所以最终在调用
		//context.contextInterface()时,所
		//获得的结果就不尽相同
	    context = new Context(new ConcreteStrategyA());
	    context.contextInterface();

	    context = new Context(new ConcreteStrategyB());
	    context.contextInterface();

	    context = new Context(new ConcreteStrategyC());
	    context.contextInterface();		

		System.out.println();
		System.out.println("**********************************************");

	}
}


