package big_talk.chapter17.adapter2;

public class Test {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        Player forwards = new Forwards("巴蒂尔");
        forwards.attack();
        
        Player guards = new Guards("麦克格雷迪");
        guards.attack();
        
        Player center = new Translator("姚明");
        
        center.attack();
        center.defense();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//球员

