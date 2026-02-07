package big_talk.chapter17.adapter2;

import lombok.Getter;
import lombok.Setter;
public class ForeignCenter {
    @Getter
    @Setter
    private String name;
    public void 进攻(){
        System.out.println("外籍中锋 "+this.name+" 进攻");
    }    

    public void 防守(){
        System.out.println("外籍中锋 "+this.name+" 防守");
    }
}

//翻译者类

