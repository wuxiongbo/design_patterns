package big_talk_design_patten.chapter0.animal1;

public class Animal {

	protected String name = "";
	public Animal(String name){
		this.name = name;
	}

	public Animal(){
		this.name="无名";
	}

	protected int shoutNum = 3;
	public void setShoutNum(int value){
		this.shoutNum=value;
	}
	public int getShoutNum(){
		return this.shoutNum;
	}

	public String shout(){
		return "";
	}
}
