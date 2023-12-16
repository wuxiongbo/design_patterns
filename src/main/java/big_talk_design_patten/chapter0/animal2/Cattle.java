package big_talk_design_patten.chapter0.animal2;

public class Cattle extends Animal {

	public Cattle (){
		super();
	}
	public Cattle (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "哞";
	}
}
