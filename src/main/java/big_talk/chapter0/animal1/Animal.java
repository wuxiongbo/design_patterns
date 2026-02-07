package big_talk.chapter0.animal1;

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

	protected int shoutNum = 3;
	public String shout(){
		return "";
	}
}
