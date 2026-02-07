package big_talk.chapter8.factorymethod;

public class ConcreteCreatorB extends Creator{
	public Product factoryMethod(){
		return new ConcreteProductB();
	}
}

