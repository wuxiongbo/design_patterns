package big_talk.chapter26.flyweight2;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        WebSiteFactory f = new WebSiteFactory();

        WebSite fx = f.getWebSiteCategory("产品展示");  
        fx.use();

        WebSite fy = f.getWebSiteCategory("产品展示");
        fy.use();

        WebSite fz = f.getWebSiteCategory("产品展示");
        fz.use();

        WebSite fl = f.getWebSiteCategory("博客");
        fl.use();

        WebSite fm = f.getWebSiteCategory("博客");
        fm.use();

        WebSite fn = f.getWebSiteCategory("博客");
        fn.use();

        System.out.println("网站分类总数为:"+f.getWebSiteCount()); //统计实例化个数，结果应该为2

        System.out.println();
        System.out.println("**********************************************");
    }
}

//抽象的网站类

