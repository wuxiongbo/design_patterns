package big_talk.chapter22.bridge3;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println();       

        HandsetBrand ab;
        ab = new HandsetBrandM();

        ab.setHandsetSoft(new HandsetGame());
        ab.run();

        ab.setHandsetSoft(new HandsetAddressList());
        ab.run();

        HandsetBrand ab2;
        ab2 = new HandsetBrandN();

        ab2.setHandsetSoft(new HandsetGame());
        ab2.run();

        ab2.setHandsetSoft(new HandsetAddressList());
        ab2.run();

        //向扩展开放，增加的功能
        HandsetBrand ab3;
        ab3 = new HandsetBrandS();
        
        ab3.setHandsetSoft(new HandsetMusicPlay());
        ab3.run();


        System.out.println();
        System.out.println("**********************************************");
    }
}

//手机软件

