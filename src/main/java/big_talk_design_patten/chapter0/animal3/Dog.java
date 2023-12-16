package big_talk_design_patten.chapter0.animal3;

public class Dog extends Animal {

	public Dog (){
		super();
	}
	public Dog (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "汪";
	}
}
