package big_talk_design_patten.chapter0.animal1;

public class Dog extends Animal {

	public Dog (){
		super();
	}
	
	public Dog (String name){
		super(name);
	}

	public String shout(){
		String result="";
		for(int i=0;i<this.shoutNum;i++){
			result+= "汪 ";
		}
		return " 我的名字叫"+ name + " " + result;
	}
}
