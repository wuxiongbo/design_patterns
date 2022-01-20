package chapter67;

//import chapter67.demo1.v0.MyArrayList;
import chapter67.v1.MyArrayList;

import java.util.Iterator;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Thread.sleep(200L);


        Iterator<String> iter1 = list.iterator();   // 快照: a, b, c, d


        Thread.sleep(200L);
        list.remove("c");
        Thread.sleep(200L);


        Iterator<String> iter2 = list.iterator();  // 快照: a, b, d


        Thread.sleep(200L);
        list.remove("a");
        Thread.sleep(200L);


        Iterator<String> iter3 = list.iterator();  // 快照: b, d



        // 输出结果：a, b, c, d
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + " ");
        }



        System.out.println();



        // 输出结果：a, b, d
        while (iter2.hasNext()) {
            System.out.print(iter2.next() + " ");
        }



        System.out.println();



        // 输出结果：b, d
        while (iter3.hasNext()) {
            System.out.print(iter3.next() + " ");
        }

    }

}
