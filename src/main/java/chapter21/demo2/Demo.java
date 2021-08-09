package chapter21.demo2;

import org.apache.commons.lang.StringUtils;

/**
 * <p> 功能语义重复 </p>
 *
 *  在同一个项目代码中有下面两个函数：isValidIp() 和 checkIfIpValid()。
 *  尽管两个函数的命名不同，实现逻辑不同，但功能是相同的，都是用来判定 IP 地址是否合法的。
 *
 *  之所以在同一个项目中会有两个功能相同的函数，那是因为这两个函数是由两个不同的同事开发的，
 *  其中一个同事在不知道已经有了 isValidIp() 的情况下，自己又定义并实现了同样用来校验 IP 地址是否合法的 checkIfIpValid() 函数。
 *
 *  上一个例子是代码 “实现逻辑重复” ，但 “语义不重复”，我们并不认为它违反了 DRY 原则。
 *  而在这个例子中，尽管两段代码的 “实现逻辑不重复”，但 “语义重复” ，也就是 “功能重复” ，我们认为它违反了 DRY 原则。
 *
 *  存在的问题：
 *  1.增加了阅读的难度：
 *    假设我们不统一实现思路，那有些地方调用了 isValidIp() 函数，有些地方又调用了 checkIfIpValid() 函数，这就会导致代码看起来很奇怪，
 *    相当于给代码“埋坑”，给不熟悉这部分代码的同事增加了阅读的难度。
 *    同事有可能研究了半天，觉得功能是一样的，但又有点疑惑，觉得是不是有更高深的考量，才定义了两个功能类似的函数，最终发现居然是代码设计的问题。
 *  2.逻辑不统一导致bug：
 *    除此之外，如果哪天项目中 IP 地址是否合法的判定规则改变了，比如：255.255.255.255 不再被判定为合法的了，
 *    相应地，我们对 isValidIp() 的实现逻辑做了相应的修改，但却忘记了修改 checkIfIpValid() 函数。
 *    又或者，我们压根就不知道还存在一个功能相同的 checkIfIpValid() 函数，这样就会导致有些代码仍然使用老的 IP 地址判断逻辑，导致出现一些莫名其妙的 bug。
 *
 *
 *  最优方案：
 *  应该在项目中，统一一种实现思路，所有用到判断 IP 地址是否合法的地方，都统一调用同一个函数。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class Demo {

    public boolean isValidIp(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    public boolean checkIfIpValid(String ipAddress) {
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
}
