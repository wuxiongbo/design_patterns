package big_talk.chapter12.facade0;

class Facade{
    SubSystemOne one;
    SubSystemTwo two;
    SubSystemThree three;
    SubSystemFour four;

    public Facade(){
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
        four = new SubSystemFour();
    }

    public void methodA(){
        one.methodOne();
        two.methodTwo();
        three.methodThree();
        four.methodFour();
    }

    public void methodB(){
        two.methodTwo();
        three.methodThree();
    }

}

//子系统1

