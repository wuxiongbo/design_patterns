package big_talk.chapter27.interpreter2;

abstract class Expression {
    //解释器
    public void interpret(PlayContext context)
    {
        if (context.getPlayText().length() == 0) {
            return;
        }
        else {
            String playKey = context.getPlayText().substring(0, 1);
            //System.out.println("playKey:"+playKey);
        
            context.setPlayText(context.getPlayText().substring(2));

            double playValue = Double.parseDouble(context.getPlayText().substring(0, context.getPlayText().indexOf(" ")));
            context.setPlayText(context.getPlayText().substring(context.getPlayText().indexOf(" ") + 1));

            this.excute(playKey, playValue);

        }
    }
    //执行
    public abstract void excute(String key, double value);
}

//音符类

