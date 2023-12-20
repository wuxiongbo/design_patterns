package the_beauty_of_design_patterns.chapter20;

import org.apache.commons.lang.StringUtils;

/**
 * <p> KISS 原则 </p>
 *
 * 尽量保持简单。
 *
 * 三种实现方式，哪种更符合KISS原则？
 *
 * 第一种实现方式
 * 利用的是正则表达式，只用三行代码就把这个问题搞定了。它的代码行数最少，那是不是就最符合 KISS 原则呢？
 * 答案是否定的。
 * 虽然代码行数最少，看似最简单，实际上却很复杂。这正是因为它使用了正则表达式。
 * 一方面，正则表达式本身是比较复杂的，写出完全没有 bug 的正则表达本身就比较有挑战；
 * 另一方面，并不是每个程序员都精通正则表达式。对于不怎么懂正则表达式的同事来说，看懂并且维护这段正则表达式是比较困难的。
 * 这种实现方式会导致代码的可读性和可维护性变差，所以，从 KISS 原则的设计初衷上来讲，这种实现方式并不符合 KISS 原则。
 *
 * 第二种实现方式
 * 使用了 StringUtils 类、Integer 类提供的一些现成的工具函数，来处理 IP 地址字符串。
 *
 * 第三种实现方式
 * 不使用任何工具函数，而是通过逐一处理 IP 地址中的字符，来判断是否合法。
 *
 *
 * 总结：
 * 从代码行数上来说，这两种方式差不多。但是，第三种要比第二种更加有难度，更容易写出 bug。
 * 从可读性上来说，第二种实现方式的代码逻辑更清晰、更好理解。所以，在这两种实现方式中，第二种实现方式更加“简单”，更加符合 KISS 原则。
 *
 *
 *
 *
 * 关于性能：
 * 你可能会说，第三种实现方式虽然实现起来稍微有点复杂，但性能要比第二种实现方式高一些啊。
 * 从性能的角度来说，选择第三种实现方式是不是更好些呢？
 *
 * 在回答这个问题之前，我先解释一下，为什么说第三种实现方式性能会更高一些。
 * 一般来说，工具类的功能都比较通用和全面，所以，在代码实现上，需要考虑和处理更多的细节，执行效率就会有所影响。
 * 而第三种实现方式，完全是自己操作底层字符，只针对 IP 地址这一种格式的数据输入来做处理，没有太多多余的函数调用和其他不必要的处理逻辑，
 * 所以，在执行效率上，这种类似定制化的处理代码方式肯定比通用的工具类要高些。
 *
 * 不过，尽管第三种实现方式性能更高些，但我还是更倾向于选择第二种实现方法。
 * 那是因为，第三种实现方式实际上是一种过度优化。
 * 除非 isValidIpAddress() 函数是影响系统性能的瓶颈代码，
 * 否则，这样优化的 “投入产出比” 并不高，增加了代码实现的难度、牺牲了代码的可读性，性能上的提升却并不明显。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class Demo {

    // 第一种实现方式: 使用正则表达式
    public boolean isValidIpAddressV1(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    // 第二种实现方式: 使用现成的工具类
    public boolean isValidIpAddressV2(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String[] ipUnits = StringUtils.split(ipAddress, '.');
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }

    // 第三种实现方式: 不使用任何工具类
    public boolean isValidIpAddressV3(String ipAddress) {
        char[] ipChars = ipAddress.toCharArray();
        int length = ipChars.length;
        int ipUnitIntValue = -1;
        boolean isFirstUnit = true;
        int unitsCount = 0;
        for (char c : ipChars) {
            if (c == '.') {
                if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                    return false;
                }
                if (isFirstUnit && ipUnitIntValue == 0) {
                    return false;
                }
                if (isFirstUnit) {
                    isFirstUnit = false;
                }
                ipUnitIntValue = -1;
                unitsCount++;
                continue;
            }
            if (c < '0' || c > '9') {
                return false;
            }
            if (ipUnitIntValue == -1) {
                ipUnitIntValue = 0;
            }
            ipUnitIntValue = ipUnitIntValue * 10 + (c - '0');
        }
        if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
            return false;
        }
        if (unitsCount != 3) {
            return false;
        }
        return true;
    }
}
