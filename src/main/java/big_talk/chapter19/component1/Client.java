package big_talk.chapter19.component1;

import big_talk.chapter19.component1.component.impl.ConcreteCompany;
import big_talk.chapter19.component1.component.impl.FinanceDepartment;
import big_talk.chapter19.component1.component.impl.HrDepartment;

/**
 * @author bear
 */
public class Client {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        ConcreteCompany root = new ConcreteCompany("北京总公司");
        root.add(new HrDepartment("总公司人力资源部"));
        root.add(new FinanceDepartment("总公司财务部"));


        ConcreteCompany comp = new ConcreteCompany("上海华东分公司");
        comp.add(new HrDepartment("华东分公司人力资源部"));
        comp.add(new FinanceDepartment("华东分公司财务部"));
        root.add(comp);


        ConcreteCompany comp2 = new ConcreteCompany("南京办事处");
        comp2.add(new HrDepartment("南京办事处人力资源部"));
        comp2.add(new FinanceDepartment("南京办事处财务部"));
        comp.add(comp2);


        ConcreteCompany comp3 = new ConcreteCompany("杭州办事处");
        comp3.add(new HrDepartment("杭州办事处人力资源部"));
        comp3.add(new FinanceDepartment("杭州办事处财务部"));
        comp.add(comp3);


        System.out.println("结构图：");
        root.display(1);
        System.out.println("职责：");
        root.lineOfDuty();


		System.out.println();
		System.out.println("**********************************************");

	}
}



