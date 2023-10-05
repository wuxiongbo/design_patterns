package my_demo.iterable;

public class Main {
    public static void main(String[] args) {
        MyIterable<String> myIterable = new MyIterable<>(String.class);

        myIterable.set(0,"aaa");
        myIterable.set(1,"bbb");
        myIterable.set(2,"ccc");


        // 实现 Iterable 接口，即可成为 增强for 的目标。 最终会调用 Iterator
        for (String s : myIterable) {
            System.out.println(s);
        }

    }
}
