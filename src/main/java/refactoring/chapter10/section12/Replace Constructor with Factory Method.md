# Replace Constructor with Factory Method （以⼯⼚函数取代构造函数）

你希望在创建对象时不仅仅是做简单的建构动作。 

将构造函数替换为⼯⼚函数。

```java
class Employee {
    private int _type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    Employee(int type) {
        _type = type;
    }
}
```

```java
class Employee {
    private int _type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;
    
    private Employee(int type) {
        _type = type;
    }
    
    // 工厂函数
    static Employee create(int type) {
        return new Employee(type);
    }
}
```

## 动机

使⽤ Replace Constructor with Factory Method （304）的最显⽽易⻅的动机，就是，在派⽣⼦类的过程中，以‘⼯⼚函数’取代‘类型码’。
你可能常常要根据‘类型码’创建相应的对象，现在，创建名单中还得加上⼦类，那些⼦类也是根据‘类型码’来创建。
然⽽，由于‘构造函数’只能返回单⼀类型的对象，因此，你需要将‘构造函数’替换为‘⼯⼚函数’ ［Gang of Four］。
此外，如果‘构造函数’的功能，不能满⾜你的需要，也可以使⽤‘⼯⼚函数’来代替它。

‘⼯⼚函数’也是 Change Value to Reference （179）的基础。
你也可以令你的‘⼯⼚函数’根据 参数的 个数 和 类型，选择不同的创建⾏为。

## 做法

-[ ] 新建⼀个⼯⼚函数，让它调⽤现有的构造函数。  
-[ ] 将调⽤构造函数的代码改为调⽤⼯⼚函数。  
-[ ] 每次替换后，编译并测试。  
-[ ] 将构造函数声明为private。  
-[ ] 编译。  

## 范例：根据整数（实际是类型码）创建对象

⼜是那个单调乏味的例⼦：员⼯薪资系统。
我以Employee表示 “员⼯”；
```java
class Employee {
    private int _type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    public Employee(int type) {
        _type = type;
    }
}
```

我希望为Employee提供不同的⼦类，并分别给予它们相应的类型码。
因此，我需要建⽴⼀个⼯⼚函数：
```java
class Employee {
    private int _type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;
    
    private Employee(int type) {
        _type = type;
    }

    // 工厂函数
    static Employee create(int type) {
        return new Employee(type);
    }

}
```

然后，我要修改构造函数的所有调⽤点，让它们改⽤上述新建的⼯⼚函数，并将构造函数声明为private：
```java
class Client {
    void create() {
        Employee eng = Employee.create(Employee.ENGINEER);
    }
}


```

## 范例：根据字符串创建⼦类对象

迄今为⽌，我还没有获得什么实质收获。⽬前的好处在于：我把 “对象创建请求的接收者” 和 “被创建对象所属的类” 分开了。
如果我随后使⽤ Replace Type Code with Subclasses （223）
把 类型码 转换为 Employee的⼦类，就可以运⽤⼯⼚函数，将这些⼦类对⽤户隐藏起来：
```java
class Employee {
    private int _type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;
    private Employee(int type) {
        _type = type;
    }
    
    // 工厂函数
    static Employee create(int type) {
        return switch (type) {
            case ENGINEER -> new Engineer();
            case SALESMAN -> new Salesman();
            case MANAGER -> new Manager();
            default -> throw new IllegalArgumentException("Incorrect type code value");
        };
    }


}
```

可惜的是，这⾥⾯有⼀个switch语句。
如果我添加⼀个新的⼦类，就必须记得更新这⾥的switch语句，⽽我⼜偏偏很健忘。
绕过这个switch语句的⼀个好办法是使⽤ class.forName()。
第⼀件要做的事是修改参数类型，这从根本上说是 Rename Method （273）的⼀种变体。
⾸先，我得建⽴⼀个函数，让它接收⼀个字符串参数：
```java
class Employee {
    private int _type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;
    private Employee(int type) {
        _type = type;
    }
    
    // create()函数 String版
    static Employee create(String name) {
        try {
            return (Employee) Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to instantiate" + name);
        }
    }

    // create()函数 int版
    static Employee create(int type) {
        return switch (type) {
            case ENGINEER -> new Engineer();
            case SALESMAN -> new Salesman();
            case MANAGER -> new Manager();
            default -> throw new IllegalArgumentException("Incorrect type code value");
        };
    }


}
```

然后，让稍早那个 “create()函数int版”  调⽤ 新建的 “create()函数String版”
```java
class Employee {
    
    private int _type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;
    private Employee(int type) {
        _type = type;
    }
    
    // create()函数 String版
    static Employee create(String name) {
        try {
            return (Employee) Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to instantiate" + name);
        }
    }
    
    // 让 “create()函数int版” 调⽤ “create()函数String版”
    static Employee create(int type) {
        return switch (type) {
            case ENGINEER -> create("Engineer");
            case SALESMAN -> create("Salesman");
            case MANAGER -> create("Manager");
            default -> throw new IllegalArgumentException("Incorrect type code value");
        };
    }
}
```
然后，我得修改 create()函数int版 的 调⽤者，将调用方的代码语句，修改为：
```java
class Client {
    void create() {
        Employee eng = Employee.create("Engineer");
    }
}

```

完成之后，我就可以将 “create()函数int版” 移除了。

现在，当我需要添加新的Employee⼦类时，就不再需要更新 create()函数了。
但我却因此失去了编译期检验，使得⼀个⼩⼩的拼写错误就可能造成运⾏期错误。 
如果有必要防⽌运⾏期错误，我会使⽤ 明确函数 来 创建对象（⻅本⻚下节）。
但这样⼀来，每添加⼀个新的⼦类，我就必须添加⼀个新函数。
这就是为了类型安全⽽牺牲掉的灵活性。
还好，即使我做了错误选择，也可以使⽤ Parameterize Method （283） 或 Replace Parameter with Explicit Methods （285）撤销决定。

另⼀个必须谨慎使⽤ class.forName() 的原因是：它向⽤户暴露了⼦类名称。 
不过这并不太糟糕，因为你可以使⽤其他字符串，并在⼯⼚函数中执⾏其他⾏为。

这也是不使⽤ Inline Method （117）去除⼯⼚函数的⼀个好理由。


## 范例：以明确函数创建⼦类

我可以通过另—⼀条途径来隐藏⼦类——使⽤明确函数。
如果你只有少数⼏个⼦类，⽽且它们都不再变化，这条途径是很有⽤的。
我可能有个抽象的Person类，它 有两个⼦类：Male 和 Female。
⾸先，我在超类中为每个⼦类定义⼀个⼯⼚函数：
```java
// 超类
class Person {
    
    // 子类1的工厂函数
    static Person createMale() {
        return new Male();
    }
    
    // 子类2的工厂函数
    static Person createFemale() {
        return new Female();
    }
}
```

然后，我可以把下⾯的(构造函数)调⽤：  
`Person kent = new Male();`  
替換成(工厂函数的调用)：  
`Person kent = Person.createMale();`  

但是，这就使得 ‘超类’ 必须知晓 ‘⼦类’。
如果，想避免这种情况，你需要⼀个更为复杂的设计，例如 Product Trader (操盘手) 模式［Baumer and Riehle］。
绝⼤多数情况下，你并不需要如此复杂的设计，上⾯介绍的做法 已经绰绰有余。