package big_talk.chapter7.proxy0;

class RealSubject implements ISubject {
	
	public void request(){
		System.out.println("真实的请求。");
	}

}

//Proxy类

