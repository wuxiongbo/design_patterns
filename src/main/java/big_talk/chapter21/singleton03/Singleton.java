package big_talk.chapter21.singleton03;

class Singleton {

    private static Singleton instance = new Singleton();

    //构造方法private化
    private Singleton() {
    }

    //得到Singleton的实例（唯一途径）
    public static Singleton getInstance() {
        return instance;
    }
}

