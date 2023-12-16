package big_talk_design_patten.chapter15.abstractfactory4;

public class DataAccess {

    private static String db = "Sqlserver";//数据库名称，可替换成Access
    //private static String db ="Access";

    //创建用户对象工厂
    public static IUser createUser(){
        IUser result = null;
        switch(db){
            case "Sqlserver":
                result = new SqlserverUser();
                break;
            case "Access":
                result = new AccessUser();
                break;
        }
        return result;
    }

    //创建部门对象工厂
    public static IDepartment createDepartment(){
        IDepartment result = null;
        switch(db){
            case "Sqlserver":
                result = new SqlserverDepartment();
                break;
            case "Access":
                result = new AccessDepartment();
                break;
        }
        return result;
    }
    
}
