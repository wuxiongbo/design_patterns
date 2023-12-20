package the_beauty_of_design_patterns.chapter28.v2;


import org.junit.Assert;
import org.junit.Test;

/**
 * <p>测试用例</p>
 * <p>
 * 1.如果字符串只包含数字：“123”，toNumber() 函数输出对应的整数：123。
 * 2.如果字符串是空或者 null，toNumber() 函数返回：null。
 * 3.如果字符串包含首尾空格：“ 123”，“123 ”，“ 123 ”，toNumber() 返回对应的整数：123。
 * 4.如果字符串包含多个首尾空格：“ 123 ”，toNumber() 返回对应的整数：123；
 * 5.如果字符串包含非数字字符：“123a4”，“123 4”，toNumber() 返回 null；
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class TextTest {
    @Test
    public void testToNumber() {
        Text text = new Text("123");
        Assert.assertEquals(Integer.valueOf(123), text.toNumber());
    }

    @Test
    public void testToNumber_nullOrEmpty() {
        Text text1 = new Text(null);
        Assert.assertNull(text1.toNumber());

        Text text2 = new Text("");
        Assert.assertNull(text2.toNumber());
    }

    @Test
    public void testToNumber_containsLeadingAndTrailingSpaces() {
        Text text1 = new Text(" 123");
        Assert.assertEquals(Integer.valueOf(123), text1.toNumber());

        Text text2 = new Text("123 ");
        Assert.assertEquals(Integer.valueOf(123), text2.toNumber());

        Text text3 = new Text(" 123 ");
        Assert.assertEquals(Integer.valueOf(123), text3.toNumber());
    }

    @Test
    public void testToNumber_containsMultiLeadingAndTrailingSpaces() {
        Text text1 = new Text("  123");
        Assert.assertEquals(Integer.valueOf(123), text1.toNumber());

        Text text2 = new Text("123  ");
        Assert.assertEquals(Integer.valueOf(123), text2.toNumber());

        Text text3 = new Text("  123  ");
        Assert.assertEquals(Integer.valueOf(123), text3.toNumber());
    }

    @Test
    public void testToNumber_containsInvalidCharacters() {
        Text text1 = new Text("123a4");
        Assert.assertNull(text1.toNumber());

        Text text2 = new Text("123 4");
        Assert.assertNull(text2.toNumber());
    }
}
