package big_talk.chapter8.factorymethod;

class ConcreteCreatorA extends Creator{
	public Product factoryMethod(){
		return new ConcreteProductA();
	}
}

