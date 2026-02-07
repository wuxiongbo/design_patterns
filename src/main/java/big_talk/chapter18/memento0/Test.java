package big_talk.chapter18.memento0;

public class Test {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        //Originator初始状态，状态属性为"On"
        Originator o = new Originator();
        o.setState("On");
        o.show();

        Caretaker c = new Caretaker();
        //保存状态时，由于有了很好的封装，可以隐藏Originator的实现细节
        c.setMemento(o.createMemento());

        //Originator改变了状态属性为"Off"
        o.setState("Off");
        o.show();

        //恢复原初始状态
        o.recoveryMemento(c.getMemento());
        o.show();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//发起人类

