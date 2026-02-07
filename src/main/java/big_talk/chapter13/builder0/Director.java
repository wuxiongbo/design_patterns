package big_talk.chapter13.builder0;

public class Director{
    public void construct(Builder builder){
        builder.buildPartA();
        builder.buildPartB();
    }
}

