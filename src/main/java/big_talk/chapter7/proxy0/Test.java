package big_talk.chapter7.proxy0;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		
		Proxy proxy = new Proxy();
		proxy.request();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//ISubject接口

