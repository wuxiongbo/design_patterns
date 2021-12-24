package chapter53.demo1.v2;

import chapter53.demo1.v2.node.impl.File;
import chapter53.demo1.v2.node.impl.Directory;

/**
 * <p>描述类的信息</p>
 *
 *
 * 单纯从功能实现角度来说，FileSystemNode的代码没有问题，已经实现了我们想要的功能。
 * 但是，如果我们开发的是一个大型系统，从
 *      扩展性（文件或目录可能会对应不同的操作）、
 *      业务建模（文件和目录从业务上是两个概念）、
 *      代码的可读性（文件和目录区分对待更加符合人们对业务的认知）
 * 的角度来说，我们最好对文件和目录进行区分设计，定义为 File 和 Directory 两个类。
 *
 *
 * 现在，文件类 和 目录类 都设计好了，我们来看，如何用它们来表示一个文件系统中的目录树结构。
 *
 *
 * 全部实现完成后，我们对照着这个例子，再重新看一下组合模式的定义：
 *      “将一组对象（文件和目录）组织成树形结构，以表示一种‘部分 - 整体’的层次结构（目录与子目录的嵌套结构）。
 *      组合模式让客户端可以统一单个对象（文件）和组合对象（目录）的处理逻辑（递归遍历）。”
 *
 *
 * 实际上，刚才讲的这种组合模式的设计思路，与其说是一种设计模式，倒不如说是对业务场景的一种数据结构和算法的抽象。
 * 其中，数据可以表示成树这种数据结构，业务需求可以通过在树上的递归遍历算法来实现。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public class Demo {
    public static void main(String[] args) {
        /**
         * /
         * /wz/
         * /wz/a.txt
         * /wz/b.txt
         * /wz/movies/
         * /wz/movies/c.avi
         * /xzg/
         * /xzg/docs/
         * /xzg/docs/d.txt
         */
        // 往 根目录 添加 文件夹 /wz/  /xzg/
        Directory fileSystemTree = new Directory("/");
        Directory node_wz = new Directory("/wz/");
        Directory node_xzg = new Directory("/xzg/");
        fileSystemTree.addSubNode(node_wz);
        fileSystemTree.addSubNode(node_xzg);



        // 往/wz/ 添加文件
        File node_wz_a = new File("/wz/a.txt");
        File node_wz_b = new File("/wz/b.txt");
        node_wz.addSubNode(node_wz_a);
        node_wz.addSubNode(node_wz_b);

        // 往/wz/ 添加 movies/文件夹
        Directory node_wz_movies = new Directory("/wz/movies/");
        node_wz.addSubNode(node_wz_movies);
        // 往/wz/movies 添加文件
        File node_wz_movies_c = new File("/wz/movies/c.avi");
        node_wz_movies.addSubNode(node_wz_movies_c);



        // 往/xzg/ 添加 docs/ 文件夹
        Directory node_xzg_docs = new Directory("/xzg/docs/");
        node_xzg.addSubNode(node_xzg_docs);
        // 往/xzg/docs/ 添加文件
        File node_xzg_docs_d = new File("/xzg/docs/d.txt");
        node_xzg_docs.addSubNode(node_xzg_docs_d);


        System.out.println("/ files num:" + fileSystemTree.countNumOfFiles());
        System.out.println("/wz/ files num:" + node_wz.countNumOfFiles());
    }
}
