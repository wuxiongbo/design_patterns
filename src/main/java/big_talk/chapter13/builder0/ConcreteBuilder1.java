package big_talk.chapter13.builder0;

import java.util.ArrayList;

class ConcreteBuilder1 extends Builder {
    private Product product = new Product();

    public void buildPartA(){
        product.add("部件A");
    }
    public void buildPartB(){
        product.add("部件B");
    }
    public Product getResult(){
        return product;
    }
}

//具体建造者2

