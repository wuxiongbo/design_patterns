package big_talk.chapter26.flyweight2;

import java.util.Hashtable;

public class WebSiteFactory {
    private Hashtable<String,WebSite> flyweights = new Hashtable<String,WebSite>();

    //获得网站分类
    public WebSite getWebSiteCategory(String key)
    {
        if (!flyweights.contains(key))
            flyweights.put(key, new ConcreteWebSite(key));
        return (WebSite)flyweights.get(key);
    }

    //获得网站分类总数
    public int getWebSiteCount()
    {
        return flyweights.size();
    }
}

