package big_talk.chapter27.interpreter0;

import java.util.ArrayList;

class Context {
    private String input;
    public String getInput(){
        return this.input;
    }
    public void setInput(String value){
        this.input = value;
    }
    
    private String output;
    public String getOutput(){
        return this.output;
    }
    public void setOutput(String value){
        this.output = value;
    }
}

