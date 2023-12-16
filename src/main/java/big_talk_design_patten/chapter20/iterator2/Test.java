package big_talk_design_patten.chapter20.iterator2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        ArrayList<String> bus = new ArrayList<String>();
        bus.add("大鸟");
        bus.add("小菜");
        bus.add("行李");
        bus.add("老外");
        bus.add("公交内部员工");
        bus.add("小偷");
        
        System.out.println("foreach遍历:");
        for(String item : bus){

            System.out.println(item + "，请买车票!");

        }
        
        System.out.println();
    
        System.out.println("Iterator遍历:");
        Iterator<String> conductor = bus.iterator();
        while (conductor.hasNext()) {
            System.out.println(conductor.next() + "，请买车票!");
        }
        
        System.out.println();
        
        System.out.println("ListIterator逆向遍历:");
        ListIterator<String> conductorDesc = bus.listIterator(bus.size());

        while (conductorDesc.hasPrevious()) {
        
            System.out.println(conductorDesc.previous() + "，请买车票!");
        
        }

		System.out.println();
		System.out.println("**********************************************");

	}
}


