package big_talk.chapter0.animal2;

public class Sheep extends Animal {

	public Sheep (){
		super();
	}
	public Sheep (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "咩";
	}
}
