package big_talk.chapter21.singleton02;

class Singleton {

    private volatile static Singleton instance;

    //构造方法private化
    private Singleton() {
    }

    //得到Singleton的实例（唯一途径）
    public static Singleton getInstance() {

        if (instance == null){

            synchronized(Singleton.class){

                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

