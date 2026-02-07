package big_talk.chapter8.factorymethod;

public class ConcreteCreatorA extends Creator{
	public Product factoryMethod(){
		return new ConcreteProductA();
	}
}

