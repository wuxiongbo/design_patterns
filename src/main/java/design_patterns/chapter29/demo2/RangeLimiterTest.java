package design_patterns.chapter29.demo2;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <p>描述类的信息</p>
 *
 *
 * 如果，在编写单元测试时, 使用同一对象,
 * 那么，当前对象修改可能会影响到接下来的测试,
 * 所以，在测试时要重置对象
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class RangeLimiterTest {

    @Test
    public void testMove_betweenRange() {
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertTrue(rangeLimiter.move(1));
        assertTrue(rangeLimiter.move(3));
        assertTrue(rangeLimiter.move(-5));
    }

    @Test
    public void testMove_exceedRange() {
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertFalse(rangeLimiter.move(6));
    }

}
