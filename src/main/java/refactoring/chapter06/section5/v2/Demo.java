package refactoring.chapter06.section5.v2;

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


    public void test(){
        final boolean isMacOS = platform.toUpperCase().indexOf("MAC") > -1;
        final boolean isIEBrower = browser.toUpperCase().indexOf("IE") > -1;
        final boolean wasResized = resize > 0;

        if (isMacOS && isIEBrower && wasInitialized() && wasResized) {
            // do something
        }

    }
}
