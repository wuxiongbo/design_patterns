# Remove Setting Method（移除设值函数）
类中的某个字段应该在对象创建时被设值，然后就不再改变。 

去掉该字段的所有设值函数。


```puml
class Employee{
  setImmutableValue()
}
```

```puml
class Employee{
}
```

## 动机

如果你为某个字段提供了设值函数，这就暗⽰这个字段值可以被改变。
如果你不希望在对象创建之后此字段还有机会被改变，那就不要为它提供设值函数（同时，将该字段设为final）。
这样你的意图会更加清晰，并且可以排除其值被修改的可能性————这种可能性往往是⾮常⼤的。

如果你保留了间接访问变量的⽅法，就可能经常有程序员盲⽬使⽤它们［Beck］。
这些⼈甚⾄会在构造函数中使⽤设值函数！
我猜想他们或许是为了代码的⼀致性，但却忽视了设值函数往后可能带来的混淆。

## 做法

-[ ] 检查设值函数被使⽤的情况，看它是否只被构造函数调⽤，或者被构造函数 所调⽤的另⼀个函数调⽤。
-[ ] 修改构造函数，使其直接访问设值函数所针对的那个变量。
•如果某个⼦类通过设值函数给超类的某个private宇段设了值，那么你就不 能这样修改。这种情况下你应该试着在超类中提供⼀个protected函数（最 好是构造函数）来给这些字段设值。不论你怎么做，都不要给超类中的函 数起⼀个与设值函数混淆的名字。

-[ ] 编译，测试。 
-[ ] 移除这个设信函数，将它所针对的字段设为 Final。
① 本步骤必须在本重构的最后进⾏，详情请看 http://www.refactoring.com/catalog/removeSetting
Method.html和稍后的译者注。—译者注
-[ ] 编译，测试。


## 范例

下⾯是⼀个简单例⼦：
```java
class Account{
  private String _id;
  Account(String id){
    setId(id);
  }
  void setId(String arg){
    _id = arg;
  }
}
```
以上代码可修改为：
```java
class Account{
  private final String _id;
  Account(String id){
    _id = id;
  }
}
```

问题可能以⼏种不同的形式出现。
⾸先，你可能会在设值函数中对传⼊参数做运算：
```java
class Account{
  private String _id;
  Account(String id){
    setId(id);
  }
  void setId(String arg){
    _id = "ZZ" + arg;
  }
}
```

如果对参数的运算很简单（就像上⾯这样）⽽且⼜只有⼀个构造函数，我可以直接在构造函数中做相同的修改。
如果修改很复杂，或者有⼀个以上的函数调⽤它，就需要提供⼀个独⽴函数。
我需要为新函数起个好名字，清楚表达该函数的⽤途：
```java
class Account {
    
    private final String _id; // 译者注：这里的final修饰符必须去掉

    Account(String id) {
        initializeId(id);
    }

    private void initializeId(String arg) {
        _id = "ZZ" + arg;
    }
    
}
```
① 此时不能将独⽴函数中要赋值的字段————即此处的 _id字段 ————声明为final，否则不能通过编译。
因此这⼀段所描述的重构⼿法实际上并不成⽴：
Account在重构后仍然是可变对象，唯⼀能够得到的好处是：
通过修改设值函数的名称，可以让读者明⽩ initializeId函数 只应该⽤于对象构造阶段。⼀-译者注


如果⼦类需要对超类的private变量赋初值，情况就⽐较麻烦⼀些：
```java
class InterestAccount extends Account{
  private double _interestRate;
  InterestAccount(String id, double rate){
    setId(id);
    _interestRate = rate;
  }
}
```

问题是我⽆法在 InterestAccount()中直接访问id变量。
最好的解决办法就 是使⽤超类构造函数：

```java
class InterestAccount extends Account{
  private double _interestRate;
  InterestAccount(String id, double rate){
    super(id);
    _interestRate = rate;
  }
}

```
如果不能那样做，那么使⽤⼀个命名良好的函数就是最好的选择：

```java
class InterestAccount extends Account{
  private double _interestRate;
  InterestAccount(String id, double rate){
    initializeId(id);
    _interestRate = rate;
  }
}

```
另⼀种需要考虑的情况就是对⼀个集合设值：

```java
class Person{
  private Vector _courses;
  Vector getCourses(){
    return _courses;
  }
  void setCourses(Vector arg){
    _courses = arg;
  }
    private Vector _courses;
}

```

在这⾥，我希望将 设值函数 替换为 add操作 和 remove操作。
我已经在  Encapsulate Collection （208）中谈到了这⼀点。