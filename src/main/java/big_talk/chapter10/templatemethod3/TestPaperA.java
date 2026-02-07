package big_talk.chapter10.templatemethod3;

class TestPaperA extends TestPaper {
    //试题1
    protected String answer1() {
        return "b";
    }
    //试题2
    protected String answer2() {
        return "a";
    }
    //试题3
    protected String answer3() {
        return "c";
    }
}

//学生乙答的试卷

