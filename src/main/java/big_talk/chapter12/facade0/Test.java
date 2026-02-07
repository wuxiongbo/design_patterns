package big_talk.chapter12.facade0;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        Facade facade = new Facade();
        
        facade.methodA();
        facade.methodB();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//外观类
//它需要了解所有的子系统的方法或属性，进行组合，以备外界调用

