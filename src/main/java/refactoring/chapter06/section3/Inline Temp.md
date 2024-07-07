# Inline Temp（内联临时变量）

你有⼀个临时变量，只被⼀个简单表达式赋值⼀次，⽽它妨碍了其他重构⼿法。 

将所有对该变量的引⽤动作，替换为对它赋值的那个表达式⾃身。
```java
  public boolean moreThan1000(Order anOrder) {
    double basePrice = anOder.basePrice();
    return (basePrice > 1000);
  }
```
```java
  public boolean moreThan1000(Order anOrder) {
    return (anOrder.basePrice() > 1000);
  }

```

## 动机

Inline Temp（119）多半是作为 Replace Temp with Query（120）的⼀部分使⽤的，所以真正的动机出现在后者那⼉。
唯⼀单独使⽤Inline Temp（119）的情况是：
你发现某个临时变量被赋予某个函数调⽤的返回值。
⼀般来说，这样的临时变量不会有任何危害，可以放⼼地把它留在那⼉。
但如果这个临时变量妨碍了其他的重构⼿法，例如Extract Method （110），你就应该将它内联化。

做法

-[ ] 检查给临时变量赋值的语句，确保等号右边的表达式没有副作⽤。 
-[ ] 如果这个临时变量并未被声明为Eina1，那就将它声明为final，然后编译。
  >这可以检查该临时变量是否真的只被賦值⼀次。 

-[ ] 找到该临时变量的所有引⽤点，将它们替换为“为临时变量赋值”的表达式。

-[ ] 每次修改后，编译并测试。 
-[ ] 修改完所有引⽤点之后，删除该临时变量的声明和赋值语句。 
-[ ] 编译，测试。
