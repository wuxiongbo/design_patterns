package big_talk.chapter13.builder0;

import java.util.ArrayList;

class Director{
    public void construct(Builder builder){
        builder.buildPartA();
        builder.buildPartB();
    }
}

