package big_talk.chapter13.builder0;

import java.util.ArrayList;

class Product{
    ArrayList<String> parts = new ArrayList<String>();

    //添加新的产品部件
    public void add(String part){
        parts.add(part);
    }
    //列举所有产品部件
    public void show(){
        for(String part : parts){
            System.out.println(part);
        }
    }
}

//抽象的建造者类

