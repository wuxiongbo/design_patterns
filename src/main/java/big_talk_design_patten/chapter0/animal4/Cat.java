package big_talk_design_patten.chapter0.animal4;

public class Cat extends Animal {


	public Cat (){
		super();
	}
	public Cat (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "喵";
	}
}
