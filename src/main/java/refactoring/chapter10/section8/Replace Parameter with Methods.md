# Replace Parameter with Methods（以函数取代参数）

对象调⽤某个函数，并将所得结果作为参数，传递给另⼀个函数。 
⽽接受该参数的函数本身，也能够调⽤前⼀个函数。


让参数接受者去除该项参数，并直接调⽤前⼀个函数。
```java
int basePrice = _quantity * _itemPrice;
double discountLevel = getDiscountLevel();
double finalPrice = discountedPrice(basePrice, discountLevel);
```

getDiscountLevel() 函数，可以放到 discountedPrice()函数中调用， 这样就可以去掉 discountLevel 参数。
```java
int basePrice = _quantity * _itemPrice;
// double discountLevel = getDiscountLevel();
double finalPrice = discountedPrice(basePrice);
```

## 动机

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
如果函数可以通过其他途径获得参数值，那么它就不应该通过参数取得该值。 
过⻓的参数列会增加程序阅读者的理解难度，因此我们应该尽可能缩短参数列的⻓度。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
缩减参数列的办法之⼀就是：看看参数接受端是否可以通过与调⽤端相同的计算来取得参数值。
如果调⽤端通过其所属对象内部的另⼀个函数来计算参数，并在计算过程中末曾引⽤调⽤端的其他参数，
那么你就应该可以将这个计算过程转移到被调⽤端内，从⽽去除该项参数。
如果你所调⽤的函数⾪属另⼀对象，⽽该对象拥有调⽤端所属对象的引⽤，前⾯所说的这些也同样适⽤。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
但是，如果参数值的计算过程依赖于调⽤端的某个参数，那么你就⽆法去掉被调⽤端的参数，
因为每⼀次调⽤动作中，该参数值都可能不同
（当然，如果你能够运⽤ Replace Parameter with Explicit Methods（285）将该参数替换为⼀个函数，⼜另当别论）。
另外，如果参数接受端并没有参数发送端对象的引⽤，⽽你也不想加上这样⼀个引⽤，那么也⽆法去除参数。 

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
有时候，参数的存在是为了将来的灵活性。
这种情况下，我仍然会把这种多余参数拿掉。
是的，你应该只在必要关头才添加参数，预先添加的参数很可能并不是你所需要的。
不过，对于这条规则（以函数取代参数），也有⼀个例外：
如果修改接⼝会对整个程序造成⾮常痛苦的结果（例如需要很⻓时间来重新构建程序，或需要修改⼤量代码），
那么可以考虑保留前⼈预先加⼊的参数。
如果真是这样，你应该⾸先判断修改接⼝究竞会造成多严重的后果，然后考虑是否应该降低系统各部位之间的依赖，以减少修改接⼝所造成的影响。
稳定的接⼝确实很好，但是被冻结在⼀个不良接⼝上也是有问题的。

