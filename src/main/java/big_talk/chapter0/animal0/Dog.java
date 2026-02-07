package big_talk.chapter0.animal0;

import lombok.Getter;
import lombok.Setter;
public class Dog {

	private String name = "";
	public Dog(String name){
		this.name = name;
	}

	public Dog(){
		this.name="无名";
	}

	@Getter

	@Setter

	private int shoutNum = 3;
	public String shout(){
		String result="";
		for(int i=0;i<this.shoutNum;i++){
			result+= "汪 ";
		}
		return " 我的名字叫"+ name + " " + result;
	}
}