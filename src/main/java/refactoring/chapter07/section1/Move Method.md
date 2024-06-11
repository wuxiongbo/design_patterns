# 搬移函数

你的程序中，有个函数与其所驻类之外的另⼀个类进⾏更多交流：调⽤后者，或被后者调⽤。


**在该函数最常引⽤的类中，建⽴⼀个有着类似⾏为的新函数。**
**将旧函数变成⼀个单纯的委托函数，或是将旧函数完全移除。**
```mermaid
classDiagram
    class Class1 {
        aMethod()
    }

    class Class2 {
    }

```

```mermaid
classDiagram
    class Class1 {
    }

    class Class2 {
        aMethod()
    }

```

## 动机

“搬移函数”是重构理论的⽀柱。
如果⼀个类有太多⾏为，或者⼀个类与另⼀个类有太多合作⽽形成⾼度耦合，我就会搬移函数。
通过这种⼿段，可以使系统中的类更简单，这些类最终也将更⼲净利落地实现系统交付的任务。  

我常常会浏览类的所有函数，从中寻找这样的函数：
使⽤另⼀个对象的次数 ⽐ 使⽤⾃⼰所驻对象的次数 还多。
⼀旦 我移动了⼀些字段，就该做这样的检查。
⼀旦 发现有可能搬移的函数，我就会观察 调⽤它的那⼀端、它调⽤的那⼀端，以及继承体系中它的任何⼀个重定义函数。

然后，会根据 “这个函数 与 哪个对象 的交流⽐较多”，决定其移动路径。
这往往不是容易做出的决定。  
如果不能肯定是否应该移动⼀个函数，我就会继续观察其他函数。移动其他函数往往会让这项决定变得容易⼀些。
有时候，即使你移动了其他函数，还是很难对眼下这个函数做出决定。其实这也没什么⼤不了的。 
如果真地很难做出决定，那么或许 “移动这个函数与否” 并不那么重要。
所以，我会凭本能去做，反正以后总是可以修改的。


## 做法

-[ ] 检查源类中被源函数所使⽤的⼀切特性（包括字段和函数），考虑它们是否也该被搬移。
  >如果某个特性只被你打算搬移的那个函数⽤到，就应该将它⼀并搬移。  
   如果另有其他函数使⽤了这个特性，你可以考虑将使⽤该特性的所有函数全都⼀并搬移。   
   有时候，‘搬移⼀组函数’ ⽐ ‘逐⼀搬移’ 简单些。

-[ ] 检查源类的⼦类和超类，看看是否有该函数的其他声明。
  >如果出现其他声明，你或许⽆法进⾏搬移，除⾮⽬标类也同样表现出多态性。 

-[ ] 在⽬标类中声明这个函数。
  >你可以为此函数选择⼀个新名称------对⽬标类更有意义的名称。

-[ ] 将源函数的代码复制到⽬标函数中。调整后者，使其能在新家中正常运⾏。
  >如果⽬标函数使⽤了源类中的特性，你得决定如何从⽬标函数引⽤源对象。  
   如果⽬标类中没有相应的引⽤机制，就把源对象的引⽤当作参数，传给新建⽴的⽬标函数。  
   如果**源函数**包含异常处理，你得判断逻辑上应该由哪个类来处理这⼀异常。  
   如果应该由源类来负责，就把异常处理留在原地。

-[ ] 编译⽬标类。

-[ ] 决定如何从源函数正确引⽤⽬标对象。
  >可能会有⼀个现成的字段或函数帮助你取得⽬标对象。   
   如果沒有，就看能否轻松建⽴⼀个这样的函数。  
   如果还是不⾏，就得在源类中新建⼀个字段来保存⽬标对象。  
   这可能是⼀个永久性修改，但你也可以让它是暂时的，因为后继的其他重构项⽬可能会把这个新建字段去掉

-[ ] 修改源函数，使之成为⼀个纯委托函数。 

-[ ] 编译，测试。 

-[ ] 决定是否删除源函数，或将它当作⼀个委托函数保留下来。
  >如果你经常要在源对象中引⽤⽬标函数，那么将源函数作为委托函数保留下来会⽐较简单。 

-[ ] 如果要移除源函数，请将源类中对源函数的所有调⽤，替换为对⽬标函数的调⽤。
  >你可以每修改⼀个引⽤点就编译并测试⼀次。  
   也可以通过⼀次“查找/替换”改掉所有引⽤点，这通常简单⼀些。

-[ ] 编译，测试。 

