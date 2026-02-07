package big_talk.chapter26.flyweight3;

import java.util.Hashtable;

class User{
    private String name;
    public User(String value){
        this.name=value;
    }

    public String getName(){
        return this.name;
    }
}

//抽象的网站类

