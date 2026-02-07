package big_talk.chapter9.prototype5;

import lombok.Getter;
import lombok.Setter;
public class WorkExperience implements Cloneable {
	//工作时间范围
	@Getter
	@Setter
	private String timeArea;
	//所在公司
	@Getter
	@Setter
	private String company;
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

