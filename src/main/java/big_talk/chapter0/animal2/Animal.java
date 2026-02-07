package big_talk.chapter0.animal2;

import lombok.Getter;
import lombok.Setter;
public class Animal {

	protected String name = "";
	public Animal(String name){
		this.name = name;
	}

	public Animal(){
		this.name="无名";
	}

	@Getter
	@Setter
	protected int shoutNum = 3;
	public String shout(){
		String result="";
		for(int i=0;i<this.shoutNum;i++){
			result+= getShoutSound()+", ";
		}
		return "我的名字叫"+ name + " " + result;
	}

	protected String getShoutSound(){
		return "";
	}

}
