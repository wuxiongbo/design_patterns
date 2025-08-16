package big_talk.chapter8.calculator1;

import java.util.Scanner;

//加法工厂
public class AddFactory implements IFactory {

    public Operation createOperation(){
        return new Add();
    }

    public static class Add extends Operation {

        public double getResult(double numberA, double numberB){
            return numberA + numberB;
        }

    }

    public static class Div extends Operation {
        public double getResult(double numberA, double numberB){
            if (numberB == 0){
                System.out.println("除数不能为0");
                throw new ArithmeticException();
            }
            return numberA / numberB;
        }
    }

    //除法工厂
    public static class DivFactory implements IFactory {

        public Operation createOperation(){
            return new Div();
        }

    }



    public static class Mul extends Operation {
        public double getResult(double numberA, double numberB){
            return numberA * numberB;
        }
    }

    //乘法工厂
    public static class MulFactory implements IFactory {

        public Operation createOperation(){
            return new Mul();
        }

    }

    public abstract static class Operation {

        public double getResult(double numberA, double numberB){
            return 0d;
        }

    }

    public static class OperationFactory {

        public static Operation createOperate(String operate){
            Operation oper = null;
            IFactory factory = null;
            switch (operate) {
                case "+":
                    factory = new AddFactory();
                    break;
                case "-":
                    factory = new SubFactory();
                    break;
                case "*":
                    factory = new MulFactory();
                    break;
                case "/":
                    factory = new DivFactory();
                    break;
            }
            oper = factory.createOperation();

            return oper;
        }

    }

    public static class Sub extends Operation {
        public double getResult(double numberA, double numberB){
            return numberA - numberB;
        }
    }

    //减法工厂
    public static class SubFactory implements IFactory {

        public Operation createOperation(){
            return new Sub();
        }

    }

    public static class Test {

        public static void main(String[] args){

            System.out.println("**********************************************");
            System.out.println("《大话设计模式》代码样例");
            System.out.println();

            try {
                Scanner sc = new Scanner(System.in);

                System.out.println("请输入数字A：");
                double numberA = Double.parseDouble(sc.nextLine());
                System.out.println("请选择运算符号(+、-、*、/)：");
                String strOperate = sc.nextLine();
                System.out.println("请输入数字B：");
                double numberB = Double.parseDouble(sc.nextLine());

                Operation oper = OperationFactory.createOperate(strOperate);

                double result = oper.getResult(numberA,numberB);

                System.out.println("结果是："+result);
            }
            catch(Exception e){
                System.out.println("您的输入有错："+e.toString());
            }

            System.out.println();
            System.out.println("**********************************************");

        }
    }
}

