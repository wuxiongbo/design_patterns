package big_talk.chapter22.bridge3;

abstract class HandsetBrand{
    protected HandsetSoft soft;

    //设置手机软件
    public void setHandsetSoft(HandsetSoft soft){
        this.soft=soft;
    }

    //运行
    public abstract void run();
}

//手机品牌M

