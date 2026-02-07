package big_talk.chapter26.flyweight2;

public class ConcreteWebSite extends WebSite {
    private String name = "";
    public ConcreteWebSite(String name) {
        this.name = name;
    }
    public void use() {
        System.out.println("网站分类：" + name);
    }
}

//网站工厂

