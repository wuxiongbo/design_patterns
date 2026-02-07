package big_talk.chapter9.prototype2;

class Resume  {
	private String name;
	private String sex;
	private String age;
	private String timeArea;
	private String company;

	public Resume(String name){
		this.name=name;
	}

	//设置个人信息
	public void setPersonalInfo(String sex,String age){
		this.sex=sex;
		this.age=age;
	}

	//设置工作经历
	public void setWorkExperience(String timeArea,String company){
		this.timeArea=timeArea;
		this.company=company;
	}

	//展示简历
	public void display(){
		System.out.println(this.name +" "+this.sex +" "+this.age);
		System.out.println("工作经历 "+this.timeArea +" "+this.company);
	}
}
