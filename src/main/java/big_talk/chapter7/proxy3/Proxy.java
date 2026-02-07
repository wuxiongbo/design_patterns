package big_talk.chapter7.proxy3;

class Proxy implements IGiveGift{

	private Pursuit gg;				//认识追求者
	
	public Proxy(SchoolGirl mm){	//也认识被追求者
		this.gg = new Pursuit(mm);	//代理初始化的过程，实际是追求者初始化的过程
	}

	public void giveDolls(){		//代理在送礼物
		this.gg.giveDolls();		//实质是追求者在送礼物
	}

	public void giveFlowers(){
		this.gg.giveFlowers();
	}

	public void giveChocolate(){
		this.gg.giveChocolate();
	}
}

