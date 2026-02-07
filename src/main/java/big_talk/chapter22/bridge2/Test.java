package big_talk.chapter22.bridge2;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println();       

        HandsetBrand ab;
        ab = new HandsetBrandMAddressList();
        ab.run();

        ab = new HandsetBrandMGame();
        ab.run();

        ab = new HandsetBrandNAddressList();
        ab.run();

        ab = new HandsetBrandNGame();
        ab.run();

        System.out.println();
        System.out.println("**********************************************");
    }
}

//手机品牌

