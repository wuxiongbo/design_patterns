# Tease Apart Inheritance (梳理并分解继承体系)

某个继承体系，同时承担两项责任。 


建⽴两个继承体系，并通过委托关系，让其中⼀个体系可以调⽤另⼀个体系。
```puml
Deal <|-- ActiveDeal
Deal <|-- PassiveDeal
ActiveDeal <|-- TabularActiveDeal
PassiveDeal <|-- TabularPassiveDeal
```

```puml
Deal <|-- ActiveDeal
Deal <|-- PassiveDeal

PresentationStyle <|-- TabularPresentationStyle
PresentationStyle <|-- SinglePresentationStyle

Deal -right-> PresentationStyle
```


## 动机

继承是个好东⻄，它可以明显减少⼦类中的代码量。
函数的重要性可能并不和它的⼤⼩成⽐例———在继承体系之中尤然。

不过，先别急着为这个强⼤的⼯具欢呼雀跃，因为继承也很容易被误⽤，并且 这种误⽤还很容易在开发⼈员之间蔓延。
今天你为了⼀项⼩⼩任务⽽加⼊⼀个⼩⼩的⼦类，明天⼜为另⼀项任务在继承体系的另⼀个地⽅加⼊另⼀个⼦类。
⼀个星期（或者⼀个⽉，⼀年）之后，你就会发现⾃⼰身陷泥淖，⽽且连⼀根拐杖都没有。

混乱的继承体系是⼀个严重的问题，因为它会导致重复代码，⽽后者正是程序员⽣涯的致命毒药。
它还会使修改变得困难，因为特定问题的解决策略被分散到了整个维承体系。
最终，你的代码将⾮常难以理解。
你⽆法简单地说：“这就是我的继承体系，它能计算结果。” 
⽽必须说：“它会计算出结果⋯呃，这些是⽤以表现不同表格形式的⼦类，每个⼦类⼜有⼀些⼦类针对不同的国家。”

要指出继承体系是否承担了两项不同的责任并不困难：  
如果继承体系中的 某⼀特定层级上 的 所有类，其 ⼦类名称 都以 相同的形容词 开始，
那么这个体系很可能就是承担着两项不同的责任。

## 做法

-[ ] ⾸先，识别出继承体系所承担的不同责任，
   然后，建⽴⼀个⼆维表格（或者三维乃⾄四维表格，如果你的继承体系够混乱⽽你的绘图⼯具够酷的话），
   并以坐标轴标示出不同的任务。
   我们将重复运⽤本重构，处理 两个 或 两个以上 的 维度（当然，每次只处理⼀个维度）。
-[ ]  判断哪⼀项责任更重要些，并准备将它留在当前的继承体系中。
   准备将 另⼀项责任 移到 另⼀个继承体系 中。
-[ ]  使⽤ Extract Class（149）从当前的超类提炼出⼀个新类，⽤以表示重要性稍低的责任，并在原超类中添加⼀个实例变量，⽤以保存新类的实例。 
-[ ]  对应于原继承体系中的每个⼦类，创建上述新类的⼀个⼦类。
   在原继承体系的⼦类中，将前⼀步骤所添加的实例变量，初始化为 新建⼦类的实例。
-[ ]  针对原继承体系中的每个⼦类，使⽤ Move Method （142）将其中的⾏为搬移到与之对应的新建⼦类中。 
-[ ]  当原继承体系中的某个⼦类不再有任何代码时，就将它去除。 
-[ ]  重复以上步骤，直到原继承体系中的所有⼦类都被处理过为⽌。
   观察新继承体系，看看是否有可能对它实施其他重构⼿法，例如 Pull Up Method （322）或 Pull Up Field （320）。

## 范例

让我们来看⼀个混乱的继承体系（如图12-1）

```puml
Deal <|-- ActiveDeal
Deal <|-- PassiveDeal
ActiveDeal <|-- TabularActiveDeal
PassiveDeal <|-- TabularPassiveDeal
```

Tabular adj 列成表格的；扁平的；表格式的  
Active  adj 活跃的；积极的；主动的  
Passive adj 被动的；消极的；顺从的  

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
这个继承体系之所以混乱，因为⼀开始，Deal类只被⽤来 显示单笔交易。
后来，某个⼈突发奇想地⽤它来显示⼀张交易表格。
只要稍稍⽤过 “主动交易” ActiveDeal⼦类 就会发现，继承这个类，不必做太多⼯作就可以显示⼀张表格了。
哦，还要 “被动交易”（PassiveDeal）表格是吗？ 没问题，再加⼀个⼦类就⾏了。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
两个⽉过去，表格相关代码变得愈来愈复杂，你却没有⼀个好地⽅可以放它们，因为时间太紧了。 （咳，⽼戏码！）
现在，你将很难向系统加⼊新的交易种类，因为 “交易处理” 与 “数据显示” 两块逻辑已经纠结难分了。 

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
按照本重构提出的处⽅笺，第⼀步⼯作是：识别出这个继承体系所承担的各项责任。
这个继承体系的  职责之⼀ 是捕提不同交易种类间的差异，职责之⼆ 是捕提不同显示⻛格之间的差异。
因此，我们可以得到下列表格：

| Deal         | Active Deal | Passive Deal |
|--------------|-------------|--------------|
| Tabular Deal |             |              |


&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
下⼀步，要判断 哪⼀项职责 更重要。
很明显，“交易种类” ⽐ “显示⻛格” 重要， 因此，我们把前者留在原地，把后者提炼到另⼀个继承体系中。
不过，实际⼯作中，我们可能需要将代码较多的职责留在原地，这样⼀来，需要搬移的代码数量会⽐较少。
然后，我们应该使⽤ Extract Class （149）提炼出⼀个单独的 PresentationStyle类，⽤以表示 “显示⻛格”（如图12-2）。

```puml
Deal <|-- ActiveDeal
Deal <|-- PassiveDeal
ActiveDeal <|-- TabularActiveDeal
PassiveDeal <|-- TabularPassiveDeal

Deal -right-> PresentationStyle
```

接下来，我们需要针对原继承体系中的每个⼦类，建⽴ PresentationStyle 的⼀个个⼦类（如图12-3），
并将Deal类中⽤来保存 PresentationStyle 实例的那个实例变量初始化为适当的⼦类实例：

```puml
Deal <|-- ActiveDeal
Deal <|-- PassiveDeal
ActiveDeal <|-- TabularActiveDeal
PassiveDeal <|-- TabularPassiveDeal

Deal -right-> PresentationStyle

PresentationStyle <|-- SingleActivePresentationStyle
PresentationStyle <|-- SinglePassivePresentationStyle
PresentationStyle <|-- TabularActivePresentationStyle
PresentationStyle <|-- TabularPassivePresentationStyle
```

Presentation Style 显现式样  
Passive 被动的；消极的；顺从的  
Tabular adj 列成表格的；扁平的；表格式的


&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
你可能会说：“这不是⽐原先的类数量还多了吗？难道这还能让我的⽣活更舒服？”  
⽣活往往如此：以退为进，⾛得更远。  
对⼀个纠结成团的继承体系来说，被提炼出来的另⼀个继承体系⼏乎总是可以再戏剧性地⼤量简化。
不过，⽐较安全的态度是⼀次⼀⼩步，不要过于躁进。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
现在，我们要使⽤ Move Method（142）和 Move Field（146），
将 Deal⼦类中 与显示逻辑相关的 函数 和 变量 搬移到 PresentationStyle 相应的⼦类去。
我们想不出什么好办法来模拟这个过程，只好请你⾃⼰想象。
总之，这个步骤完成后， TabularActiveDeal和 TabularPassiveDeal 不再有任何代码，因此我们将它们移除（如图12-4）。

```puml
Deal <|-- ActiveDeal
Deal <|-- PassiveDeal

Deal -right-> PresentationStyle

PresentationStyle <|-- SingleActivePresentationStyle
PresentationStyle <|-- SinglePresentationPresentationStyle
PresentationStyle <|-- TabularActivePresentationStyle
PresentationStyle <|-- TabularPresentationPresentationStyle
```

两项职责被分割之后，我们可以分别简化两个继承体系。
⼀旦本重构完成，我们总是能够⼤⼤简化被提炼出来的新继承体系，⽽且通常还可以简化原继承体系。

下⼀步，我们将摆脱 “显示⻛格” 中的  主动（active）与  被动（passive）区别，如图12-5。
```puml
Deal <|-- ActiveDeal
Deal <|-- PassiveDeal

PresentationStyle <|-- TabularPresentationStyle
PresentationStyle <|-- SinglePresentationStyle

Deal -right-> PresentationStyle
```

就连 “单⼀显示” 和 “表格显示” 之间的区别，都可以运⽤若⼲变量值来捕捉，
根本不需要为它们建⽴⼦类（如图12-6）。

