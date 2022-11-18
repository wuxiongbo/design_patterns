package my_demo.monitor.callback.hollywood.demo3.lower;

/**
 * <p>导演</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Director {

    // 我不知道你具体是谁。但我知道你是call我的人。待会儿轮到我call回你啦
    private final IPerformer whoCallMe;

    public Director(IPerformer listener) {//由构造器的参数获得引用，完成注册
        whoCallMe = listener;
    }

    public void copy() {

        // 我的进度我做主，演员们不要再问了
        for (int i = 0; i < 100; i++) {

            // 在适当的 '时机' 回调 ，这个 '时机' 由我定
            try {
                Thread.sleep((long) (100 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 时机已到，主动通知演员
            if (i % 10 == 0) {
                whoCallMe.update(i);
            }

        }

        System.out.println("copy() over");
    }
}
