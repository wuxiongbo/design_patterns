package big_talk.chapter17.adapter0;

class Adapter extends Target {

    private Adaptee adaptee = new Adaptee(); //建立一个私有的Adaptee对象

    public void request(){          //这样就可以把表面上调用request()方法
        adaptee.specificRequest();  //变成实际调用specificRequest()
    }
}

