package big_talk.chapter7.proxy3;

public class Pursuit implements IGiveGift {

	private SchoolGirl mm;
	public Pursuit(SchoolGirl mm){
		this.mm = mm;
	}

	public void giveDolls(){
		System.out.println(this.mm.getName() + ",你好！送你洋娃娃。");
	}

	public void giveFlowers(){
		System.out.println(this.mm.getName() + ",你好！送你鲜花。");
	}

	public void giveChocolate(){
		System.out.println(this.mm.getName() + ",你好！送你巧克力。");
	}
}

//代理类

