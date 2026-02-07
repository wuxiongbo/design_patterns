package big_talk.chapter17.adapter0;

public class Test {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        Target target = new Adapter();

        target.request();

        

		System.out.println();
		System.out.println("**********************************************");

	}
}

//需要适配的类

