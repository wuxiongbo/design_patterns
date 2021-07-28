package chapter20;

/**
 * <p>复杂代码也可能符合 KISS 原则 </p>
 *
 * 并不是代码行数越少就越“简单”，还要考虑逻辑复杂度、实现难度、代码的可读性等。
 * 那如果一段代码的逻辑复杂、实现难度大、可读性也不太好，是不是就一定违背 KISS 原则呢？
 *
 *
 * 这段代码来自专栏《数据结构与算法之美》中KMP 字符串匹配算法的代码实现。
 * 这段代码完全符合我们刚提到的逻辑复杂、实现难度大、可读性差的特点，但它并不违反 KISS 原则。为什么这么说呢？
 *
 * KMP 算法以快速高效著称。
 * 当我们需要处理长文本字符串匹配问题（几百 MB 大小文本内容的匹配），
 * 或者字符串匹配是某个产品的核心功能（比如 Vim、Word 等文本编辑器），
 * 又或者字符串匹配算法是系统性能瓶颈的时候，我们就应该选择尽可能高效的 KMP 算法。
 * 而 KMP 算法本身具有逻辑复杂、实现难度大、可读性差的特点。
 *
 * 本身就复杂的问题，用复杂的方法解决，并不违背 KISS 原则。
 *
 * 不过，平时的项目开发中涉及的字符串匹配问题，大部分都是针对比较小的文本。
 * 在这种情况下，直接调用编程语言提供的现成的字符串匹配函数就足够了。
 * 如果非得用 KMP 算法、BM 算法来实现字符串匹配，那就真的违背 KISS 原则了。
 * 也就是说，同样的代码，在某个业务场景下满足 KISS 原则，换一个应用场景可能就不满足了。
 *
 * 总结：
 * 是否符合 KISS 原则， 由具体场景决定
 *
 *  <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class Demo2 {

    // KMP algorithm: a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }

    // b表示模式串，m表示模式串的长度
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

}
