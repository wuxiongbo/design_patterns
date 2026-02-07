package big_talk.chapter8.factorymethod;

class ConcreteCreatorB extends Creator{
	public Product factoryMethod(){
		return new ConcreteProductB();
	}
}

