package big_talk.chapter27.interpreter1;

abstract class Expression {
    
    public void interpret(PlayContext context) {
      if (context.getPlayText().length() == 0) {
          return;
      }
      else {
        String playKey = context.getPlayText().substring(0, 1);
          
        context.setPlayText(context.getPlayText().substring(2));

        double playValue = Double.parseDouble(context.getPlayText().substring(0,context.getPlayText().indexOf(" ")));
        context.setPlayText(context.getPlayText().substring(context.getPlayText().indexOf(" ") + 1));

        this.excute(playKey, playValue);
      }
    }
    //抽象方法“执行”，不同的文法子类，有不同的执行处理
    public abstract void excute(String key, double value);
}

//音符类

