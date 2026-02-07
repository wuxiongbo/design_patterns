package big_talk.chapter7.proxy0;

public class Proxy implements ISubject{

	private final RealSubject rs;

	public Proxy(){
		this.rs = new RealSubject();
	}				
	
	public void request(){		
		this.rs.request();
	}
}

