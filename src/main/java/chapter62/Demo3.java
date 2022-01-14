package chapter62;

import chapter62.depencence.Content;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
class SensitiveWordFilter {

    // return true if content doesn't contain sensitive words.
    public boolean filter(Content content) {

        if (!filterSexyWord(content)) {
            return false;
        }

        if (!filterAdsWord(content)) {
            return false;
        }

        if (!filterPoliticalWord(content)) {
            return false;
        }

        return true;
    }



    private boolean filterSexyWord(Content content) {
        //....
        return false;
    }

    private boolean filterAdsWord(Content content) {
        //...
        return false;
    }

    private boolean filterPoliticalWord(Content content) {
        //...
        return false;
    }
}