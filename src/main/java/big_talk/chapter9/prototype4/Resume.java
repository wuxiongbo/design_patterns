package big_talk.chapter9.prototype4;

public class Resume implements Cloneable {
	private final String name;
	private String sex;
	private String age;
	private final WorkExperience work;		//声明一个工作经历的对象
	public Resume(String name){
		this.name = name;
		this.work = new WorkExperience();//对这个工作经历对象实例化
	}
	//设置个人信息
	public void setPersonalInfo(String sex,String age){
		this.sex=sex;
		this.age=age;
	}
	//设置工作经历
	public void setWorkExperience(String timeArea,String company){
		this.work.setTimeArea(timeArea);//给工作经历实例的时间范围赋值
		this.work.setCompany(company);	//给工作经历实例的公司赋值
	}
	//展示简历
	public void display(){
		System.out.println(this.name +" "+this.sex +" "+this.age);
		System.out.println("工作经历 "+this.work.getTimeArea() +" "+this.work.getCompany());
	}
	public Resume clone(){
		Resume object = null;
		try {
			object = (Resume)super.clone();
		}
		catch(CloneNotSupportedException exception){
			System.err.println("Clone异常。");
		}
		return object;
	}
}

//工作经历类

