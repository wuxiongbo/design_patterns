# 提炼⼦类

类中的某些特性只被某些（⽽⾮全部）实例⽤到。 


新建⼀个⼦类，将上⾯所说的那⼀部分特性移到⼦类中。
```puml
class JobItem{
    getTotalPrice()
    getUnitPrice()
    getEmployee()
}
```
```puml
class JobItem{
    getTotalPrice()
    getUnitPrice()
}

class LaborItem extends JobItem{
    getEmployee()
}
```


# 动机

使⽤ Extract Subclass（330）的主要动机是：
你发现，类中的某些⾏为，只被 ⼀部分实例 ⽤到，其他实例 不需要它们。  

有时候, 这种⾏为上的差异是通过 `类型码` 区分的，
此时，你可以使⽤ Replace Type Code with Subclasses （223） 或 Replace Type Code with State/Strategy （227）。  
但是，并⾮⼀定要出现了 `类型码` 才表示需要考虑使⽤‘⼦类’。  

Extract Class（149）是 Extract Subclass（330）之外的另⼀种选择，
两者之间的抉择，其实就是 ‘委托’ 和 ‘继承’ 之间的 抉择。
Extract Subclass（330）通常更容易进⾏，但它也有限制：
⼀旦对象创建完成，你⽆法再改变与类型相关的⾏为。
但，如果使⽤ Extract Class（149），你只需插⼊ 另⼀个组件 ，就可以改变对象的⾏为。
此外，⼦类 只能⽤以表现 ⼀组变化。
如果，你希望⼀个类以⼏种不同的⽅式变化，就必须使⽤ ‘委托’。



# 做法

-[ ] 为源类定义⼀个新的⼦类。 
-[ ] 为这个新的⼦类提供构造函数。
>→简单的做法是：让⼦类构造函数接受与超类构造函数相同的参数，并通过super调⽤超类构造函数。
>→如果你希望对⽤户隐藏⼦类的存在，可使⽤Replace Constructor with Factory Method （304）.

-[ ] 找出调⽤超类构造函数的所有地点。如果它们需要的是新建的⼦类，令它们改⽽调⽤新构造函数。 
>→如果⼦类构造函数需要的参数和超类构造函数的参数不同，可以使⽤ Rename Method （273）修改其参数列。  
  如果⼦类构造函数不需要超炎构造函数的某些参数，可以使⽤Rename Method（273）将它们去除。
>→如果不再需要直接创建超类的实例，就将超类声明为抽象类.

-[ ] 逐⼀使⽤ Push Down Method （328）和 Push Down Field （329）将源类的特性移到⼦类去。
>•和 Extract Class（149）不同的是，先处理函数再处理数据，通常会简单⼀些.
>•当⼀个public函数被下移到⼦类后，你可能需要重新定义该函数的调⽤端的局部变量或参数类型，让它们改⽽调⽤⼦类中的新函数。
> 如果忘记进⾏这⼀步骤，编译器会提醒你。

-[ ] 找到所有这样的字段：它们所传达的信息如今可由继承体系⾃身传达（这⼀类字段通常是boolean变最或类型码）。
以Sel/ Encapsulate Field（171）避免直接使⽤这些字段，然后将它们的取值函数替换为多态常量函数。
所有使⽤这些字段的地⽅都应该以 Replace Conditional with Polymorphism（255）重构。
>•任何函数如果位于源类之外，⽽⼜使⽤了上述宇段的访问函数，考虑以Move Method （142）將它移到源类中，
> 然后再使⽤ Replace Conditional with Polymorphism （255）.

-[ ] 每次下移之后，编译并测试。

# 范例
下⾯是JobItem类，⽤来决定当地修⻋⼚的⼯作报价：
```java
class JobItem {
    private int _unitPrice;
    private int _quantity;
    private Employee _employee;
    private boolean _isLabor;

    public JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
        _unitPrice = unitPrice;
        _quantity = quantity;
        _isLabor = isLabor;
        _employee = employee;
    }

    public int getTotalPrice() {
        return getUnitPrice() * _quantity;
    }

    public int getUnitPrice() {
        return _isLabor ? _employee.getRate() : _unitPrice;
    }

    public int getQuantity() {
        return _quantity;
    }

    public Employee getEmployee() {
        return _employee;
    }
}
```

```java
class Employee {
    private int _rate;

    public Employee(int rate) {
        _rate = rate;
    }

    public int getRate() {
        return _rate;
    }
}
```

我要提炼出⼀个 LaborItem⼦类，因为上述某些 ⾏为 和 数据，只在按⼯时（labor）收费的情况下才需要。
⾸先，建⽴这样⼀个类：
```java
class LaborItem extends JobItem {}
```

我需要为 LaborItem 提供⼀个构造函数，因为 JobItem 没有 默认构造函数。
我把 超类构造函数的参数列 复制过来：
```java
class LaborItem extends JobItem {
    public LaborItem(int unitPrice, 
                     int quantity, 
                     boolean isLabor, 
                     Employee employee) {
        super(unitPrice, quantity, isLabor, employee);
    }
}
```
这就⾜以让新的⼦类通过编译了。
但是，这个构造函数会造成混淆：某些参数是 LaborItem 所需要的，另⼀些不是。
稍后，我再来解决这个问题。

下⼀步是要找出对 JobItem 构造函数 的调⽤，并从中找出可以改⽤ LaborItem 构造函数的地⽅。
因此，下列语句：
```java
JobItem j1 = new JobItem(0, 5, true, kent);
```
就被修改为：
```java
JobItem j1 = new LaborItem(0, 5, true, kent);
```
此时，我尚未修改变量类型，只是修改了构造函数所属的类。
之所以这样做，是因为，我希望只在必要地点才使⽤新类型。
到⽬前为⽌，⼦类还没有专属接⼝，因此，我还不想宣布任何改变。

现在，正是清理构造函数参数列的好时机。
我将针对每个构造函数使⽤ Rename Method（273）。
⾸先，处理超类构造函数。
我要新建⼀个构造函数，并把旧构造函数声明为protected（不能直接声明为private，因为⼦类还需要它）：
```java
class JobItem {
    // 旧构造函数
    protected JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
        _unitPrice = unitPrice;
        _quantity = quantity;
        _isLabor = isLabor;
        _employee = employee;
    }

    // 新构造函数
    public JobItem(int unitPrice, int quantity) {
        this(unitPrice, quantity, false, null);
    }
}
```

现在，外部调⽤应该使⽤新构造函数：
```java
JobItem j2 = new JobItem(10, 15);
```

编译、测试都通过后，我再使⽤ Rename Method（273）修改⼦类构造函数：
```java
class LaborItem extends JobItem {
    public LaborItem(int quantity, Employee employee) {
        super(0, quantity, true, employee);
    }
}
```

此时，我仍然暂时使⽤ protected的超类构造函数。
现在，我可以将JobItem的特性向下搬移。
先从函数开始，我先运⽤ Push Down Method（328）对付 getEmployee（）函数：
```java
class LaborItem extends JobItem {
    public Employee getEmployee() {
        return _employee;
    }
}

class JobItem {
    protected Employee _employee;
}

```

因为 _employee字段 也将在稍后被下移到 LaborItem，所以，我现在先将它声明为 protected.
将 _employee字段 声明为 protected 之后，我可以再次清理构造函数，
让 _employee 只在 即将去达的⼦类中 被初始化：
```java
class JobItem {
    // 清理构造函数的参数 _employee
    protected JobItem(int unitPrice, int quantity, boolean isLabor) {
        _unitPrice = unitPrice;
        _quantity = quantity;
        _isLabor = isLabor;
    }
}
```
```java
class LaborItem extends JobItem {
    public LaborItem(int quantity, Employee employee) {
        super(0, quantity, true);
        // 初始化 _employee
        _employee = employee;
    }
}
```

_isLabor字段 所传达的信息，现在已经成为继承体系的内在信息，因此我可以移除这个字段了。

最好的⽅式是：先使⽤ Self Encapsulate Field（171），然后再修改访问函数，
改⽤`多态常量函数`——这样的函数，会在不同的⼦类实现版本中返回不同的固定值：
```java
class JobItem {
    protected boolean isLabor() {
        return false;
    }
}
```
```java
class LaborItem extends JobItem {
    protected boolean isLabor() {
        return true;
    }
}
```
然后，我就可以摆脱 _isLabor字段 了。
现在，我可以观察 isLabor（）函数的⽤户，并运⽤ Replace Conditional with Polymorphism （255）重构它们。
我找到了下列这样的函数：

```java
class JobItem {
    public int getUnitPrice() {
        return isLabor() ? _employee.getRate() : _unitPrice;
    }
}
```
将它重构为：
```java
class JobItem {
    public int getUnitPrice() {
        return _unitPrice;
    }
}

class LaborItem extends JobItem {
    public int getUnitPrice() {
        return _employee.getRate();
    }
}
```

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
当使⽤某项字段的函数全被下移⾄⼦类后，我就可以使⽤ Push Down Field （329）将字段也下移。
如果尚⽆法移动字段，那就表示我需要对函数做更多处理，
可能需要实施 Push Down Method（328）或 Replace Conditional with Polymorphism（255）。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
由于，只有按零件收费的⼯作项才会⽤到 _unitPrice字段，
所以，我可以再次运⽤ Extract Subclass（330）对 JobItem 提炼出⼀个 ⼦类：PartsItem。
完成后，我可以将JobItem声明为抽象类。