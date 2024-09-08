# Replace Delegation with Inheritance （以继承取代委托）

你在两个类之间使⽤委托关系，并经常为整个接⼝ 编写许多极简单的委托函数。


让委托类继承受托类。




## 动机

本重构与 Replace Inheritance with Delegation （352）恰恰相反。
如果你发现⾃⼰需要使⽤受托类中的所有函数，并且费了很⼤⼒⽓编写所有极简的委托函数，本重构可以帮助你轻松回头使⽤继承。

两条告诚需牢记于⼼。

⾸先，如果你并没有使⽤受托类的所有函数，那么就不应该使⽤ Replace Delegation With Inheritance（35S），
因为⼦类应该总是遵循超类的接⼝。
如果过多的委托函数让你烦⼼，你有别的选择：你可以通过Remove Middle Man （160）让客户端⾃⼰调⽤受托函数，
也可以使⽤Extract Superclass（336）将两个类接⼝相同的部分提炼到超类中，然后让两个类都维承这个新的超类；
你还可以⽤类似的⼿法使⽤Extract Interface （34I）。

另⼀种需要当⼼的情况是：
受托对象被不⽌⼀个其他对象共享，⽽且受托对象是可变的。
在这种情况下，你就不能将委托关系替换为继承关系，因为这样就⽆法 再共享数据了。
数据共享是必须由委托关系承担的⼀种责任，你⽆法把它转给继承 关系。
如果受托对象是不可变的，数据共享就不成问题，因为你⼤可放⼼地复制对象，谁都不会知道。


## 做法

⼝ 让委托端成为受托端的⼀个⼦类。 
⼝编译。 
•此时，某些函数可能会发⽣冲突：它们可能有相同的名称，但在返回类型.

异常指定或可⻅程度⽅⾯有所差异。你可以使⽤ Rename Method （273）解决 此类问题。

⼝ 将受托字段设为该字段所处对象本身。 
⼝ 去掉简单的委托函数。 
⼝ 编译并测试。 
⼝ 将所有其他涉及委托关系的代码，改为调⽤对象⾃身。 
⼝ 移除受托字段。



## 范例

下⾯是⼀个简单的Employee类，将⼀些函数委托给另⼀个同样简单的Person类：
```java
class Employee{
    Person _person = new Person();
    public String getName(){
        return _person.getName();
    }
    public void setName(String arg){
        _person.setName(arg);
    }
    public String toString(){
        return "Emp: " + _person.getLastName();
    }
}

class Person{
    String _name;
    public String getName(){
        return _name;
    }
    public void setName(String arg){
        _name = arg;
    }
    public String getLastName(){
        return _name.substring(_name.lastIndexOf(" ") + 1);
    }
}
```

第⼀步，只需声明两者之间的继承关系：
`class Employee extends Person`

此时，如果有任何函数发⽣冲突，编译器会提醒我。
如果某⼏个函数的名称相同，但返回类型不同，或抛出不同的异常，它们之间就会出现冲突。
所有此类问题都可以通过 Rename Method （273）加以解决。为求简化，我没有在范例中列出这些麻烦情况。 
下⼀步要将受托字段设值为该字段所处对象⾃身。同时，我必须先删掉所有简单的委托函数（例如getName（）和setName（））。
如果留下这种函数，就会因为⽆限递归⽽引起系统调⽤栈溢出。
在此范例中，我应该把Employee的getName（）和 setName（）拿掉。

⼀旦Employee可以正常⼯作了，我就修改其中⽤到委托函数的代码，让它们 直接调⽤从超类继承⽽来的函数：
```java
public String toString(){
    return "Emp: " + getLastName();
}
```
摆脱所有涉及委托关系的函数后，我也就可以摆脱 _person 这个受托字段了。


