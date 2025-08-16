package big_talk.chapter19.component0;

import big_talk.chapter19.component0.component.impl.Composite;
import big_talk.chapter19.component0.component.impl.Leaf;

/**
 * @author bear
 */
public class Client {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        Composite root = new Composite("root");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        // 树枝1
        Composite comp = new Composite("Composite X");
        comp.add(new Leaf("Leaf XA"));
        comp.add(new Leaf("Leaf XB"));        
        root.add(comp);

        // 树枝1 的 子树枝 树枝2
        Composite comp2 = new Composite("Composite XY");
        comp2.add(new Leaf("Leaf XYA"));
        comp2.add(new Leaf("Leaf XYB"));
        comp.add(comp2);

        Leaf leaf = new Leaf("Leaf C");
        root.add(leaf);

        Leaf leaf2 = new Leaf("Leaf D");
        root.add(leaf2);
        root.remove(leaf2);


        root.display(1);


		System.out.println();
		System.out.println("**********************************************");

	}
}



