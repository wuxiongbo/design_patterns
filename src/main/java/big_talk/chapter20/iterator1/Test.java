package big_talk.chapter20.iterator1;

public class Test {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        ConcreteAggregate bus = new ConcreteAggregate();
        bus.add("大鸟");
        bus.add("小菜");
        bus.add("行李");
        bus.add("老外");
        bus.add("公交内部员工");
        bus.add("小偷");

        //正序迭代器
        //Iterator conductor = new ConcreteIterator(bus);
        //倒序迭代器
        Iterator conductor = new ConcreteIteratorDesc(bus);

        conductor.first();
        while (!conductor.isDone()) {
            System.out.println(conductor.currentItem() + "，请买车票!");
            conductor.next();
        }

		System.out.println();
		System.out.println("**********************************************");

	}
}

//聚集抽象类

