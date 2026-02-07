package big_talk.chapter9.prototype5;

class WorkExperience implements Cloneable {
	//工作时间范围
	private String timeArea;
	public String getTimeArea(){
		return this.timeArea;
	}
	public void setTimeArea(String value){
		this.timeArea=value;
	}

	//所在公司
	private String company;
	public String getCompany(){
		return this.company;
	}
	public void setCompany(String value){
		this.company=value;
	}

	public WorkExperience clone(){
		WorkExperience object = null;
		try {
			object = (WorkExperience)super.clone();
		}
		catch(CloneNotSupportedException exception){
			System.err.println("Clone异常。");
		}
		return object;
	}
}

