package big_talk.chapter27.interpreter2;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        PlayContext context = new PlayContext();
        //音乐-上海滩
        System.out.println("音乐-上海滩：");
        context.setPlayText("T 500 O 2 E 0.5 G 0.5 A 3 E 0.5 G 0.5 D 3 E 0.5 G 0.5 A 0.5 O 3 C 1 O 2 A 0.5 G 1 C 0.5 E 0.5 D 3 ");

        Expression expression=null;    
        while (context.getPlayText().length() > 0) {
            String str = context.getPlayText().substring(0, 1);

            switch (str) {
                case "O":
                    expression = new Scale();
                    break;
                case "C":
                case "D":
                case "E":
                case "F":
                case "G":
                case "A":
                case "B":
                case "P":
                    expression = new Note();
                    break;
                case "T":
                    expression = new Speed();
                    break;

            }
            expression.interpret(context);
        }
        
        System.out.println();
        System.out.println();
        System.out.println("**********************************************");
    }
}

//演奏内容

