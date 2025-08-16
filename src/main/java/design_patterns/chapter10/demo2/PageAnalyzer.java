package design_patterns.chapter10.demo2;

/**
 * <p>适合用 “组合” 的场景</p>
 *
 * Crawler 类和 PageAnalyzer 类，它们都用到了 URL 拼接和分割的功能，但并不具有继承关系（既不是父子关系，也不是兄弟关系）。
 *
 * 仅仅为了代码复用，生硬地抽象出一个父类出来，会影响到代码的可读性。
 *
 * 如果不熟悉背后设计思路的同事，发现 Crawler 类和 PageAnalyzer 类继承同一个父类，而父类中定义的却只是 URL 相关的操作，
 * 会觉得这个代码写得莫名其妙，理解不了。这个时候，使用组合就更加合理、更加灵活。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class PageAnalyzer {

    private Url url; // 组合

    public PageAnalyzer() {
        this.url = new Url();
    }
    //..

}
