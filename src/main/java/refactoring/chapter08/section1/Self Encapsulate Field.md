# 字段⾃我封装

你直接访问⼀个字段，但与字段之间的耦合关系逐渐变得笨拙。

为这个字段建⽴取值/设值函数，并且只以这些函数来访问宇段。


```java
class IntRange {
    private int _low, _high;
    boolean includes(int arg) {
        return arg >= _low && arg <= _high;
    }
}
```

```java
class IntRange {
    
    private int _low, _high;

    boolean includes(int arg) {
        return arg >= getLow() && arg <= getHigh();
    }

    int getLow() {
        return _low;
    }

    int getHigh() {
        return _high;
    }
}

```


## 动机
在 “字段访问⽅式” 这个问题上，存在两种截然不同的观点：
其中⼀派认为，在该变量定义所在的类中，你可以⾃由访问它；
另⼀派认为，即使在这个类中，你也应该只使⽤访问函数间接访问。

两派之间的争论可以说是如⽕如茶。（参⻅Auer在[Auer]p.413 和 Beck在[Beck]上的讨论。）

归根结底，  
间接访问变量的好处是，⼦类可以通过覆写⼀个函数⽽改变获取数据的途径：
它还⽀持更灵活的数据管理⽅式，例如，延迟初始化（意思是：只有在需要⽤到某值时，才对它初始化）。

直接访问变量的好处则是：代码⽐较容易阅读。
阅读代码的时候，你不需要停下来说：“啊，这只是个取值函数。”

⾯临选择时，我总是做两⼿准备。
通常情况下, 我会很乐意按照团队中其他⼈的意愿来做。
就我⾃⼰⽽⾔，我⽐较喜欢先使⽤ 直接访问⽅式，直到这种⽅式给我带来麻烦为⽌，此时，我就会转⽽使⽤ 间接访问⽅式。

重构，给了我改变主意的⾃由。
如果你想访问超类中的⼀个字段，却⼜想在⼦类中将对这个变量的访问改为⼀个计算后的值，
这就是最该使⽤ Self Encapsulate Field（171）的时候。

“字段⾃我封装” 只是第⼀步。
完成⾃我封装之后，你可以在⼦类中，根据⾃⼰的需要，随意 ‘覆写’ 取值/设值 函数。


## 做法

-[ ] 为待封装字段建⽴取值/设值函数。

-[ ] 找出该字段的所有引⽤点，将它们全部改为调⽤取值/设值函数。
   >• 如果，引⽤点要读取字段值，就将它替换为调⽤ 取值函数； 
      如果，引⽤点要给字段赋值，就将它替换为调⽤ 设值函数。  
      你可以暂时将该字段改名，让编译器帮助你查找引⽤点。

-[ ] 将该字段声明为private。

-[ ] 复查，确保找出所有引⽤点。

-[ ] 编译，测试。


```java
class IntRange {
    private int _low, _high;
    IntRange(int low, int high) {
        _low = low;
        _high = high;
    }
    
    boolean includes(int arg) {
        return arg >= _low && arg <= _high;
    }
    void grow(int factor) {
        _high = _high * factor;
    }
}
```

为了封装_low和_high这两个字段，我先定义 取值/设值函数（如果此前没有定义的话），并使⽤它们：
```java
class IntRange {
    
    private int _low, _high;
    
    void grow(int factor) {
        setHigh(getHigh() * factor);
    }
    
    boolean includes(int arg) {
        return arg >= getLow() && arg <= getHigh();
    }

    int getLow() {
        return _low;
    }

    int getHigh() {
        return _high;
    }
    
    void setLow(int arg) {
        _low = arg;
    }
    void setHigh(int arg) {
        _high = arg;
    }
    
}

```

使⽤本项重构时，你必须⼩⼼对待 “在构造函数中使⽤设值函数” 的情况。
⼀般来说，设值函数被认为应该在对象创建后才使⽤，所以，初始化过程中的⾏为有可能与设值函数的⾏为不同。
这种情况下，我也许在构造函数中直接访问字段，要不就是单独另建⼀个初始化函数：

```java
class IntRange {
    
    private int _low, _high;

    // 必须⼩⼼对待 “在构造函数中使⽤设值函数” 的情况。
    IntRange(int low, int high) {
        _low = setLow(low);
        _high = setHigh(high);
    }
    
    void grow(int factor) {
        setHigh(getHigh() * factor);
    }
    
    boolean includes(int arg) {
        return arg >= getLow() && arg <= getHigh();
    }

    int getLow() {
        return _low;
    }

    int getHigh() {
        return _high;
    }
    
    void setLow(int arg) {
        _low = arg;
    }
    void setHigh(int arg) {
        _high = arg;
    }
    
}

```


```java
class IntRange {
    
    private int _low, _high;

    // 这种情况下，要不 在构造函数中直接访问字段，要不就是 单独另建⼀个初始化函数
    IntRange(int low, int high) {
        initialize(low, high);
    }
    
    private void initialize(int low, int high) {
        _low = low;
        _high = high;
    }
    
    void grow(int factor) {
        setHigh(getHigh() * factor);
    }
    
    boolean includes(int arg) {
        return arg >= getLow() && arg <= getHigh();
    }

    int getLow() {
        return _low;
    }

    int getHigh() {
        return _high;
    }
    
    void setLow(int arg) {
        _low = arg;
    }
    void setHigh(int arg) {
        _high = arg;
    }
    
}
```

一旦你拥有一个子类，上述所有动作的价值就体现出来了。如下所示：
```java
class CappedRange extends IntRange {
    CappedRange(int low, int high, int cap) {
        super(low, high);
        _cap = cap;
    }
    
    int getCap() {
        return _cap;
    }
    
    int getHigh() {
        return Math.min(super.getHigh(), getCap());
    }
    
    private int _cap;
}
```

现在，我可以在 CappedRange 中 覆写 getHigh()，从⽽加⼊对 “范围上限”（Cap）的考虑 ⽽不必修改 IntRange 的任何⾏为。

