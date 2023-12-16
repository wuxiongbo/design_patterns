package big_talk_design_patten.chapter15.abstractfactory3;

//Sqlserver工厂
public class SqlserverFactory implements IFactory {

    public IUser createUser(){
        return new SqlserverUser();
    }
    
    public IDepartment createDepartment(){
        return new SqlserverDepartment();
    }
    
}

