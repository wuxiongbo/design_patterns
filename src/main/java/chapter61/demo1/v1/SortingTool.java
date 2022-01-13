package chapter61.demo1.v1;

/**
 * <p>描述类的信息</p>
 *
 * 需求：
 *      写一个小程序，实现对一个文件进行排序的功能。
 *      文件中只包含 整型数，并且，相邻的数字 通过 逗号 来区隔。
 *
 * 如果由你来编写这样一个小程序，你会如何来实现呢？  你可以把它当作面试题，先自己思考一下，再来看我下面的讲解。
 *
 * 思路1：
 *   你可能会说，这不是很简单嘛，只需要将文件中的内容读取出来，并且通过 逗号 分割成一个一个的数字，放到内存数组中，
 *   然后编写某种排序算法（比如快排），或者直接使用编程语言提供的排序函数，对数组进行排序，最后再将数组中的数据写入文件就可以了。
 *
 * 思路2：
 *   但是，如果文件很大呢？比如有 10GB 大小，因为内存有限（比如只有 8GB 大小），我们没办法一次性加载文件中的所有数据到内存中，
 *   这个时候，我们就要利用外部排序算法（具体怎么做，可以参看我的另一个专栏《数据结构与算法之美》中的“排序”相关章节）了。
 *
 * 思路3：
 *   如果文件更大，比如有 100GB 大小，我们为了利用 CPU 多核的优势，可以在外部排序的基础之上进行优化，加入多线程并发排序的功能，
 *   这就有点类似“单机版”的 MapReduce。
 *
 * 思路4：
 *   如果文件非常大，比如有 1TB 大小，即便是单机多线程排序，这也算很慢了。
 *   这个时候，我们可以使用真正的 MapReduce 框架，利用多机的处理能力，提高排序的效率。
 *
 *
 * 代码实现：
 *    如下
 *
 * 代码规范：
 *    在“编码规范”那一部分我们讲过， ‘函数’ 的 行数 不能过多，最好不要超过一屏的大小。
 *    所以，为了避免 sortFile() 函数过长，我们把每种排序算法从 sortFile() 函数中抽离出来，拆分成 4 个独立的排序函数。
 *
 *
 * 问题分析：
 *    如果只是开发一个简单的工具，那上面的代码实现就足够了。
 *    毕竟，代码不多，后续修改、扩展的需求也不多，怎么写都不会导致代码不可维护。
 *
 *    但是，如果我们是在开发一个大型项目，排序文件只是其中的一个功能模块，那我们就要在代码设计、代码质量上下点儿功夫了。
 *    只有每个小的功能模块都写好，整个项目的代码才能不差。
 *
 * 缺陷：
 *    1）在刚刚的代码中，我们并没有给出每种排序算法的代码实现。
 *      实际上，如果自己实现一下的话，你会发现，每种排序算法的实现逻辑都比较复杂，代码行数都比较多。
 *      所有排序算法的代码实现都堆在 Sorter 一个类中，这就会导致这个类的代码很多。
 *      而在 “编码规范” 那一部分中，我们也讲到，一个类的代码太多也会影响到可读性、可维护性。
 *
 *    2）除此之外，所有的 排序算法 都设计成 Sorter 的私有函数，也会影响代码的可复用性。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class SortingTool {

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        sorter.sortFile(args[0]);
    }

}
