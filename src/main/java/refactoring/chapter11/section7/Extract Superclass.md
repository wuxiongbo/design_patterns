# Extract Superclass（提炼超类）


两个类有相似特性。


为这两个类建⽴⼀个超类，将相同特性移⾄超类。


```puml
class Department{
getTotalAnnualCost
getName
getHeadCount
}

class Employee{
getAnnualCost
getName
getId
}
```
```puml
class Party{
getAnnualCost
getName
}

class Department{
getAnnualCost
getHeadCount
}

class Employee{
getAnnualCost
getId
}

```



# 动机

重复代码是系统中最糟糕的东⻄之⼀。
如果你在不同地⽅做同⼀件事情，⼀旦 需要修改那些动作，你就得平⽩做更多的修改。

重复代码的某种形式就是：两个类以相同的⽅式做类似的事情，或者以不同的⽅式做类似的事情。

对象提供了⼀种简化这种情况的机制，那就是继承。
但是，在建⽴这些具有共通性的类之前，你往往⽆法发现这样的共通性，因此, 经常会在具有共通性的类出现之后，再开始建⽴其间的继承结构。

另⼀种选择, 就是 Extract Class （149）。
这两种⽅案之间的选择其实就是 `继承` 和 `委托` 之间的选择。
如果两个类可以共享⾏为，也可以共享接⼝，那么，`继承` 是⽐较简单的做法。
如果你选错了，也总有 Replace Inheritance with Delegation（352）这瓶后悔药可吃。

做法

⼝ 为原本的类新建⼀个空⽩的抽象超类。

⼝ 运⽤Pull Up Field （320）、Pull Up Method （322）和 Pull Up Constructor Body（325）逐⼀将⼦类的共同元素上移到超类。 
>→先搬移字段，通常⽐较简单。   
>→如果相应的⼦类函数有不同的签名，但⽤途相同，可以先使⽤Rename Method （273）将它们的签名改⼒相同，然后再使⽤ Pull Up Method（322）.  
>→如果相应的⼦类函數有相同的签名，但函数本体不同，可以在超类中把它 们的共同签名声明为抽象函数。  
>→如果相应的⼦类函数有不同的函数本体，但⽤途相同，可试着使⽤ Substitute Algorithm （139）把其中⼀个函数的函数本体复制到另⼀个函数中。 如果运转正常，你就可以使⽤ Pull Up Method（322）.

⼝ 每次上移后，编译并测试。
⼝ 检查留在⼦类中的函数，看它们是否还有共通成分。
如果有，可以使⽤ Extract Method（110）将共通部分再提炼出来，
然后，使⽤ Pull Up Method（322）将提炼出的函数上移到超类。
如果各个⼦类中某个函数的整体流程很相似，你也许可以使⽤ Form Template Method （345）。

⼝ 将所有共通元素都上移到超类之后，检查⼦类的所有⽤户。
   如果它们只使⽤共同接口，你就可以把它们请求的对象类型改为超类。


# 范例

下⾯例中，我以Employee表⽰ “员⼯”，以Department表⽰“部门”： 

```java
class Employee{
    private String _name;
    private int _annualCost;
    private String _id;

    public Employee(String name, String id, int annualCost){
        _name = name;
        _id = id;
        _annualCost = annualCost;
    }

    public int getAnnualCost(){
        return _annualCost;
    }

    public String getId(){
        return _id;
    }

    public String getName(){
        return _name;
    }
}

class Department{
    private String _name;
    private Vector _staff = new Vector();

    public Department(String name){
        _name = name;
    }

    public int getTotalAnnualCost(){
        Enumeration e = getStaff();
        int result = 0;
        while(e.hasMoreElements()){
            Employee each = (Employee)e.nextElement();
            result += each.getAnnualCost();
        }
        return result;
    }

    public int getHeadCount(){
        return _staff.size();
    }

    public Enumeration getStaff(){
        return _staff.elements();
    }

    public void addStaff(Employee arg){
        _staff.addElement(arg);
    }
}
```

这⾥有两处共同点。⾸先，员⼯和部⻔都有名称；其次，它们都有年度成本，只不过计算⽅式略有不同。
我要提炼出⼀个超类，⽤以包容这些共通特性。
第⼀步 是新建这个超类，并将现有的两个类定义为其⼦类：

```java
abstract class Party {/*省略*/}
class Employee extends Party{/*省略*/}
class Department extends Party{/*省略*/}
```

然后，我开始把特性上移⾄超类。
先实施 Pull Up Field （320）通常会⽐较简单：
然后，我可以使⽤ Pull Up Method （322）把这个字段的取值函数也上移⾄超类：
```java
abstract class Party{
    
    protected String _name;
    
    protected Party(String name){
        _name = name;
    }
    public String getName(){
        return _name;
    }
}
```
我通常会把这个字段声明为 private。
不过，在此之前，我需要先使⽤ Pull Up Constructor Body （325），这样才能对 _name 正确赋值：
```java
abstract class Party{
    protected String _name;
    protected Party(String name){
        _name = name;
    }
    public String getName(){
        return _name;
    }
}
```
```java
class Employee extends Party{
    private int _annualCost;
    private String _id;

    public Employee(String name, String id, int annualCost){
        super(name);
        _id = id;
        _annualCost = annualCost;
    }

    public int getAnnualCost(){
        return _annualCost;
    }

    public String getId(){
        return _id;
    }
}
```
```java
class Department extends Party{
    private Vector _staff = new Vector();

    public Department(String name){
        super(name);
    }

    public int getTotalAnnualCost(){
        Enumeration e = getStaff();
        int result = 0;
        while(e.hasMoreElements()){
            Employee each = (Employee)e.nextElement();
            result += each.getAnnualCost();
        }
        return result;
    }

    public int getHeadCount(){
        return _staff.size();
    }

    public Enumeration getStaff(){
        return _staff.elements();
    }

    public void addStaff(Employee arg){
        _staff.addElement(arg);
    }
}

```

Department.getTotalAnnualCost() 和 Employee.getAnnualCost() 两个函数的⽤途相同，
因此，它们应该有相同的名称。
我先运⽤ Rename Method （273）把 它们的名称改为相同：
```java
class Department extends Party{
    private Vector _staff = new Vector();

    public Department(String name){
        super(name);
    }

    // Rename Method 
    public int getAnnualCost(){
        Enumeration e = getStaff();
        int result = 0;
        while(e.hasMoreElements()){
            Employee each = (Employee)e.nextElement();
            result += each.getAnnualCost();
        }
        return result;
    }

    public int getHeadCount(){
        return _staff.size();
    }

    public Enumeration getStaff(){
        return _staff.elements();
    }

    public void addStaff(Employee arg){
        _staff.addElement(arg);
    }
}
```

它们的函数本体仍然不同，因此我⽬前还⽆法使⽤ Pull Up Method（322）。
但是，我可以在超类 Party中，声明⼀个抽象函数：

abstract public int getAnnualCost();

这⼀步修改完成后，我需要观察两个⼦类的⽤户，看看是否可以改变它们转⽽使⽤新的超类。
⽤户之⼀就是 Department⾃身，它保存了⼀个 Employee对象集合。

Department.getAnnualCost() 只调⽤ 集合内的元素（Employee 对象）的 getAnnualCost()函数，
⽽该函数⽬前是在Party中声明的：

```java
class Department extends Party{
    private Vector _staff = new Vector();

    public Department(String name){
        super(name);
    }

    public int getAnnualCost(){
        Enumeration e = getStaff();
        int result = 0;
        while(e.hasMoreElements()){
            Employee each = (Employee)e.nextElement();
            result += each.getAnnualCost();
        }
        return result;
    }

    public int getHeadCount(){
        return _staff.size();
    }

    public Enumeration getStaff(){
        return _staff.elements();
    }

    public void addStaff(Employee arg){
        _staff.addElement(arg);
    }
}
```

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
这⼀⾏为暗示⼀种新的可能性：我可以⽤ Composite模式［Gang of Four］来对待 Department 和 Employee，
这样就可以让⼀个 Department对象 包容另⼀个 Department对象。  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
这是⼀项新功能，所以这项修改严格来说不属于重构范围。
如果⽤户恰好需要 Composite模式，我可以修改 _staff字段 名字，使其更好地表现这⼀模式。
这⼀修改还会带来其他相应修改：
修改 addStaff()函数名称，并将该函数的参数类型改为 Party。
最后，还需要把 headCount() 函数 变成⼀个递归调⽤。
我的做法是，在 Employee中，建⽴⼀个headCount()函数，让它返回1;
再使⽤ Substitute Algorithm（139）修改 Department 的 headCount() 函数，让它加总各部⻔的 headCount()调⽤结果。
