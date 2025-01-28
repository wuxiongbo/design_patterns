# Preserve Whole Object（保持对象完整）

你从 某个对象中，取出若⼲值，将它们作为 某⼀次函数调⽤时 的参数。


改为传递整个对象

```java
class Room {
    boolean withinPlan(HeatingPlan plan) {
        
        int low = daysTempRange().getLow();
        int high = daysTempRange().getHigh();
        
        return plan.withinRange(low, high);
    }
}
```

```java

class Room {
    boolean withinPlan(HeatingPlan plan) {
        return plan.withinRange(daysTempRange());
    }
}

```


## 动机
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
有时候，你会将来⾃同⼀对象的若⼲项数据作为参数，传递给某个函数。
这样做的问题在于，万⼀将来被调⽤函数需要新的数据项，你就必须查找并修改对此函数的所有调⽤。
如果你把这些数据所属的整个对象传给函数，可以避免这种尴尬的处境，因为被调⽤函数可以向那个参数对象请求任何它想要的信息。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
除了可以使参数列更稳固之外，Preserve Whole Object（288）往往还能提⾼代码的可读性。
过长的参数列很难使⽤，因为，调⽤者 和 被调⽤者 都必须记住这些参数的⽤途。
此外，不使⽤ 完整对象 也会造成重复代码，因为，被调⽤函数 ⽆法利⽤ 完整对象 中的函数 来计算某些中间值。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
不过事情总有两⾯。
如果你传的是 数值，被调⽤函数就只依赖于 这些数值，⽽不依赖 它们所属的对象。
但如果你传递的是 整个对象，被调⽤函数所在的对象就需要依赖 参数对象。
如果这会使你的依赖结构恶化，那么就不该使⽤ Preserve Whole Object（288）。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
我还听过另⼀种不使⽤ Preserve Whole Object（288）的理由：
如果 “被调⽤函数” 只需要 “参数对象” 的其中⼀项数值，那么，只传递那个数值会更好。
我并不认同这种观点，因为，传递⼀项数值 和 传递⼀个对象，⾄少在代码清晰度上是等价的
（当然对于按值传递的参数来说，性能上可能有所差异）。
更重要的考量应该放在 **对象之间的依赖关系** 上。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
如果，“被调⽤函数” 使⽤了 “来⾃另⼀个对象的很多项数据”，
这可能意味该函数实际上应该被定义在那些“数据所属的对象” 中。
所以，考虑 Preserve Whole Object（288）的同时，你也应该考虑 Move Method（142）。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
运⽤本项重构之前，你可能还没有定义⼀个完整对象。
那么，就应该先使⽤ Introduce Parameter Object（295）引入参数对象。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
还有⼀种常⻅情况：
调⽤者 将⾃⼰的若⼲数据作为参数，传递给 被调⽤函数。 
这种情况下，如果该对象有合适的取值函数，你可以使⽤ this 取代这些参数值，并且⽆需操⼼对象依赖问题。


## 范例

在以下范例中，我以⼀个 Room对象 表示“房间”，它负责记录房间⼀天中的 最⾼温度 和 最低温度。
然后，这个对象需要将 `实际的温度范围` 与 `预先规定的温度控制计划` 相⽐较，
告诉客户当天温度是否符合计划要求：

```java
class Room {
    // 房间最高和最低温度是否在计划温度区间内
    boolean withinPlan(HeatingPlan plan) {
        
        int low = daysTempRange().getLow();
        int high = daysTempRange().getHigh();
        
        return plan.withinRange(low, high);
    }

    // 房间最高和最低温度
    private int low;
    private int high;
    private TempRange daysTempRange() {
        return new TempRange(low, high);
    }
}

// 温度控制计划
class HeatingPlan {
    boolean withinRange(int low, int high) {
        return (low >= _range.getLow() && high <= _range.getHigh());
    }
    
    private TempRange _range;
    
    public void setRange(TempRange _range) {
        this._range = _range;
    }
}


// 温度区间
@Getter
@Setter
public class TempRange {
    
    private int low;
    private int high;

    public TempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

}

```

其实我不必将 TempRange对象 的信息拆开来单独传递，只需将整个对象传递给 withinPlan()函数 即可。

在这个简单的例⼦中，我可以⼀次性完成修改。
如果相关的参数更多些，我也可以进⾏⼩步重构。
⾸先，我为参数列添加新的参数项，⽤以传递完整的 TempRange对象：
```java
/**
 * 计划温度区间
 */
class HeatingPlan {
    
    // 参数列添加新的参数项，传递完整的 TempRange对象
    boolean withinRange(TempRange roomRange, int low, int high) {
        return (low >= _range.getLow() && high <= _range.getHigh());
    }
    
    private TempRange _range;
    
    public void setRange(TempRange _range) {
        this._range = _range;
    }
}

class Room {

    // 房间最高和最低温度
    private int low;
    private int high;
    private TempRange daysTempRange() {
        return new TempRange(low, high);
    }
    
    // 房间最高和最低温度是否在计划温度区间内
    boolean withinPlan(HeatingPlan plan) {
        
        int low = daysTempRange().getLow();
        int high = daysTempRange().getHigh();
        
        return plan.withinRange(daysTempRange(), low, high);
        
    }
    
}


// 温度区间
@Getter
@Setter
public class TempRange {

    private int low;
    private int high;

    public TempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }
}


```

然后，我以 TempRange对象 提供的函数 来替换 low参数：
```java
class HeatingPlan {
    
    // 用 TempRange对象 提供的 getLow()函数 来替换 low参数
    boolean withinRange(TempRange roomRange, int high) {
        return (roomRange.getLow() >= _range.getLow() && high <= _range.getHigh());
    }
    
    private TempRange _range;
    public void setRange(TempRange _range) {
        this._range = _range;
    }
}

class Room {
    
    boolean withinPlan(HeatingPlan plan) {
        int low = daysTempRange().getLow();
        int high = daysTempRange().getHigh();
        return plan.withinRange(daysTempRange(), high);
    }

    // 房间最高和最低温度
    private int low;
    private int high;
    private TempRange daysTempRange() {
        return new TempRange(low, high);
    }
}


// 温度区间
@Getter
@Setter
public class TempRange {

    private int low;
    private int high;

    public TempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

}

```
重复上述步骤，直到把所有待处理參数项都去除为⽌：

现在，我不再需要 low 和 high 这两个临时变量了：
```java
class HeatingPlan {

    // 用 TempRange对象 提供的 getHigh()函数 来替换 high参数
    boolean withinRange(TempRange roomRange) {
        return (roomRange.getLow() >= _range.getLow() 
                && roomRange.getHigh() <= _range.getHigh());
    }
    
    private TempRange _range;
    public void setRange(TempRange _range) {
        this._range = _range;
    }
}

class Room {
    boolean withinPlan(HeatingPlan plan) {
        return plan.withinRange(daysTempRange());
    }

    // 房间最高和最低温度
    private int low;
    private int high;
    private TempRange daysTempRange() {
        return new TempRange(low, high);
    }
}

// 温度区间
@Getter
@Setter
public class TempRange {

    private int low;
    private int high;

    public TempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

}

```

使⽤ TempRange 完整对象后不久，你就会发现，
可以将 某些函数(HeatingPlan#withinRange) 移到 tempRange对象 中，使它更容易被使⽤，

例如：
```java

class HeatingPlan {
    
    boolean withinRange(TempRange roomRange) {
        return _range.includes(roomRange);
    }

    private TempRange _range;
    public void setRange(TempRange _range) {
        this._range = _range;
    }
}


@Getter
@Setter
class TempRange {
    
    private int low;
    private int high;

    public TempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

    /**
     * 函数迁移
     * {@link HeatingPlan#withinRange}
     * @param range 范围
     * @return 是否包含
     */
    boolean includes(TempRange range) {
        return range.getLow() >= this.getLow() && range.getHigh() <= this.getHigh();
    }
    
}

```