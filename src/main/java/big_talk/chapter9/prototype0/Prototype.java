package big_talk.chapter9.prototype0;

public abstract class Prototype implements Cloneable {
	private final String id;

	public Prototype(String id){
		this.id=id;
	}

	public String getID(){
		return this.id;
	}

	//原型模式的关键就是有这样一个clone方法
	public Object clone(){
		Object object = null;
		try {
			object = super.clone();
		}
		catch(CloneNotSupportedException exception){
			System.err.println("Clone异常。");
		}
		return object;
	}
}

//具体原型类

