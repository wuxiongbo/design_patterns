package the_beauty_of_design_patterns.chapter66;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>描述类的信息</p>
 *
 * 如何应对遍历时改变集合导致的未决行为？
 * 有两种比较干脆利索的解决方案：
 *      一种是     遍历的时候，不允许 增、删 元素，
 *      另一种是   增、删 元素之后，让遍历报错。
 *
 * 第一种解决方案，比较难实现，
 *  我们要确定遍历开始和结束的时间点。
 *  遍历开始的时间节点我们很容易获得。我们可以把创建迭代器的时间点作为遍历开始的时间点。但是，遍历结束的时间点无法确定
 *
 * 第二种解决方法，更加合理。
 *  Java 语言就是采用的这种解决方案，增删元素之后，让遍历报错。
 *
 *
 * {@link ArrayList.Itr}
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */

public class Demo {
    public static void main(String[] args) {

        test3();

    }


    static void test1(){
        ArrayList<String> names = initList();

        Iterator<String> iterator = names.iterator();
        iterator.next();


//        names.remove("a");
        names.add(0, "x");
    }

    static void test2(){

        ArrayList<String> names = initList();

        Iterator<String> iterator = names.iterator();
        iterator.next();
        iterator.remove();

        iterator.remove(); // 报错，抛出IllegalStateException异常

    }

    static void test3(){

        ArrayList<String> names = initList();

        Iterator<String> iterator = names.iterator();

        // 正常运行
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }

    }


    static ArrayList<String> initList(){
        ArrayList<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        return names;
    }

}