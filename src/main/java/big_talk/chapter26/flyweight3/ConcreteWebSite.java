package big_talk.chapter26.flyweight3;

class ConcreteWebSite extends WebSite {
    private String name = "";
    public ConcreteWebSite(String name) {
        this.name = name;
    }
    public void use(User user) {
        System.out.println("网站分类：" + name+" 用户："+user.getName());
    }
}

//网站工厂

