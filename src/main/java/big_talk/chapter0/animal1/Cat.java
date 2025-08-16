package big_talk.chapter0.animal1;

public class Cat extends Animal {

	public Cat (){
		super();
	}

	public Cat (String name){
		super(name);
	}
	
	public String shout(){
        return " 我的名字叫"+ name + " " + "喵 ".repeat(Math.max(0, this.shoutNum));
	}
}
