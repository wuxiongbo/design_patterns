# Replace Parameter with Explicit Methods（以明确函数取代参数）

你有⼀个函数，其中完全取决于参数值⽽采取不同⾏为。 


针对该参数的每⼀个可能值，建⽴⼀个独⽴函数。
```java
void setValue(String name, int value) {
    if (name.equals("height")) {
        height = value;
        return;
    }
    if (name.equals("width")) {
        width = value;
        return;
    }
    Assert.shouldNeverReachHere();
}
```

```java
void setHeight(int arg) {
    height = arg;
}
void setWidth(int arg) {
    width = arg;
}
```

## 动机
Replace Parameter with Explicit Methods （285）恰恰相反于 Parameterize Method （283）。
如果某个参数有多种可能的值，⽽函数内⼜以条件表达式检查这些参数值，并根据不同参数值做出不同的⾏为，那么就应该使⽤本项重构。
调⽤者原本必须赋 予参数适当的值，以决定该函数做出何种响应。
现在，既然你提供了不同的函数给调⽤者使⽤，就可以避免出现条件表达式。
此外，你还可以获得编译期检查的好处，⽽且接⼝也更清楚。
如果以参数值决定函数⾏为，那么函数⽤户不但需要观察该函数，⽽且还要判断参数值是否合法，⽽“合法的参数值”往往很少在⽂档中被清楚地提出。

就算不考虑编译期检查的好处，只是为了获得⼀个清晰的接⼝，也值得你执⾏本项重构。
哪怕只是给⼀个内部的布尔变量赋值，相较之下，Switch.beOn() 也⽐ Switch.setState(true)要清楚得多。

但是，如果参数值不会对函数⾏为有太多影响，你就不应该使⽤ Replace Parameter with Explicit Methods （285）。
如果情况真是这样，⽽你也只需要通过参数为⼀个字段赋值，那么直接使⽤设值函数就⾏了。
如果的确需要条件判断的⾏为，可考虑使⽤ Replace Conditional with Polymorphism（255）。

## 范例
下列代码中，我想根据不同的参数值，建⽴ Employee 之下不同的⼦类。

以下代码往往是 Replace Constructor with Factory Method （304）的施⾏成果：
```java
class Employee {
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

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
由于这是⼀个⼯⼚函数，我不能实施 Replace Conditional with Polymorphism（255），
因为，使⽤该函数时对象根本还没创建出来。
由于，可以预见到Employee不会 有太多新的⼦类，
所以，我可以放⼼地为每个⼦类建⽴⼀个⼯⼚函数，⽽不必担⼼工⼚函数的数量会剧增。


⾸先，我要根据参数值建⽴相应的新函数：
```java
class Employee {
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    static Employee create(int type) {
        return switch (type) {
            case ENGINEER -> new Engineer();
            case SALESMAN -> new Salesman();
            case MANAGER -> new Manager();
            default -> throw new IllegalArgumentException("Incorrect type code value");
        };
    }
    
    static Employee createEngineer() {
        return new Engineer();
    }
    static Employee createSalesman() {
        return new Salesman();
    }
    static Employee createManager() {
        return new Manager();
    }
}
```

然后，把switch语句的各个分⽀替换为对新函数的调⽤：
每修改⼀个分⽀，都需要编译并测试，直到所有分⽀修改完毕为⽌：
```java
class Employee {
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    static Employee create(int type) {
        return switch (type) {
            case ENGINEER -> Employee.createEngineer();
            case SALESMAN -> Employee.createSalesman();
            case MANAGER -> Employee.createManager();
            default -> throw new IllegalArgumentException("Incorrect type code value");
        };
    }

    static Employee createEngineer() {
        return new Engineer();
    }

    static Employee createSalesman() {
        return new Salesman();
    }

    static Employee createManager() {
        return new Manager();
    }
}
```

接下来，我把注意⼒转移到 旧函数的调⽤端。
我把诸如下⾯这样的代码：
Employee kent = Employee.create(ENGINEER);
替换为：
Employee kent = Employee.createEngineer();

修改完 create()函数的 所有调⽤者之后，就可以把 create()函数 删掉了。
同时，也可以把所有常量都删掉。

```java
class Employee {
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    // delete create() method
//    static Employee create(int type) {
//        return switch (type) {
//            case ENGINEER -> Employee.createEngineer();
//            case SALESMAN -> Employee.createSalesman();
//            case MANAGER -> Employee.createManager();
//            default -> throw new IllegalArgumentException("Incorrect type code value");
//        };
//    }

    static Employee createEngineer() {
        return new Engineer();
    }

    static Employee createSalesman() {
        return new Salesman();
    }

    static Employee createManager() {
        return new Manager();
    }
}
```


