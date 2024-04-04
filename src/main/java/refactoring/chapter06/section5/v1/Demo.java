package refactoring.chapter06.section5.v1;

/**
 * @author bear
 * @date 2024/4/2 23:30
 * @description
 */
public class Demo {

    String platform = "";
    String browser = "";
    int resize = 0;
    private boolean wasInitialized() {
        return false;
    }



    public void test() {

        if ((platform.toUpperCase().indexOf("MAC") > -1) &&
                (browser.toUpperCase().indexOf("IE") > -1) &&
                wasInitialized() && resize > 0) {
            // do something
        }

    }



}
