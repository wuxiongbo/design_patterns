package demo;

// 开闭原则
public class Main {
    public static void main(String[] args) {

        ApiStatInfo apiStatInfo = new ApiStatInfo();

        // ...省略设置apiStatInfo数据值的代码

        apiStatInfo.setTimeoutCount(289); // 改动四：设置tiemoutCount值

        ApplicationContext
                //单例模式 初始化对象
                .getInstance()
                .getAlert()
                .check(apiStatInfo);
    }

}
