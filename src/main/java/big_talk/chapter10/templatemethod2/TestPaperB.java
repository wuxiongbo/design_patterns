package big_talk.chapter10.templatemethod2;

class TestPaperB extends TestPaper {
    //试题1
    public void testQuestion1() {
        super.testQuestion1();
        System.out.println("答案：d");
    }
    //试题2
    public void testQuestion2() {
        super.testQuestion2();
        System.out.println("答案：b");
    }
    //试题3
    public void testQuestion3() {
        super.testQuestion3();
        System.out.println("答案：a");
    }
}

