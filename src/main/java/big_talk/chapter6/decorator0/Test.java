package big_talk.chapter6.decorator0;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		ConcreteComponent c = new ConcreteComponent();
    	ConcreteDecoratorA d1 = new ConcreteDecoratorA();
    	ConcreteDecoratorB d2 = new ConcreteDecoratorB();

    	d1.SetComponent(c);	//首先用d1来包装c
    	d2.SetComponent(d1);//再用有来包装d1
    	d2.Operation();   	//最终执行d2的Operation()


		System.out.println();
		System.out.println("**********************************************");

	}
}

//Component类

