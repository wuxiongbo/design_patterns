# Form TemPlate Method（塑造模板函数）

你有⼀些⼦类，其中相应的某些函数以相同顾序执⾏类似的操作， 但各个操作的细节上有所不同。

**将这些操作分别放进独⽴函数中，并保持它们都有相同的签名， 于是原函数也就变得相同了。然后将原函数上移⾄超类。**

```plantuml
class Site {
}

class ResidentialSite {
    +double getBillableAmount()
}
class LifelineSite {
    +double getBillableAmount()
}

Site <|-- ResidentialSite
Site <|-- LifelineSite

note right of ResidentialSite::getBillableAmount
  double base = _units * _rale * 0.5;
  double tax = base * Site.TAX_RATE * 0.2;
  return base + lax;
end note

note right of LifelineSite::getBillableAmount
  double base = _units * _rale;
  double tax = base * Site.TAX_RATE;
  return base + lax;
end note

```

```plantuml
class Site {
  - getBillableAmount() : double 
  + getBaseAmount() : double
  + getTaxAmount() : double
}

class ResidentialSite {
  + getBaseAmount() : double
  + getTaxAmount() : double
}

class LifelineSite {
  + getBaseAmount() : double
  + getTaxAmount() : double
}

Site <|-- ResidentialSite
Site <|-- LifelineSite

note right of Site::getBillableAmount
  return getBaseAmount() + getTaxAmount();
end note

```

## 动机

继承是避免重复⾏为的⼀个强⼤⼯具。
⽆论何时，只要你看见两个⼦类之中有类似的函数，就可以把它们提升到超类。
但是如果这些函数并不完全相同该怎么办？ 
我们仍有必要尽量避免重复，但又必须保持这些函数之间的实质差异。

常见的⼀种情况是：两个函数以相同顺序执⾏⼤致相近的操作，但是各操作不完全相同。
这种情况下我们可以将执⾏操作的序列移⾄超，并借助多态保证各操作仍得以保持差异性。
这样的函数被称为 Template Method（模板函数）［Gang of Four］。


## 做法

-[ ] 在各个⼦类中分解⽬标函数，使分解后的各个函数要不完全相同，要不完全不同。

-[ ] 运⽤ Pull Up Method（322）将各⼦类内完全相同的函数上移⾄超类。

-[ ] 对于那些（剩余的、存在于各⼦类内的）完全不同的函数，实施Rename Method （273），使所有这些函数的签名完全相同。
>这将使得原函数变⼒完全相同，因为它们都执⾏同样⼀组函数调⽤；但各于类会以不同⽅式响应这些调⽤。

-[ ] 修改上述所有签名后，编译并测试。 
-[ ] 运⽤ Pull Up Method（322）将所有原函数逐⼀上移⾄超类。在超类中将那些代表各种不同操作的函数定义为抽象函数。 
-[ ] 编译，测试。 
-[ ] 移除其他⼦类中的原函数，每删除⼀个，编译并测试。

## 范例

现在我将完成第1章遗留的那个范例。
在此范例中，我有⼀个 customer，其中，有两个⽤于打印的函数。
statement（）函数以 ASCⅡ码打印报表：

```
    public String statement() {
        // add header lines
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        // 详单细目
        for (Rental rental : _rentals) {
            // show figures for this rental
            result.append("\t")
                    .append(rental.getMovie().getTitle()).append("\t")
                    .append(rental.getCharge()).append("\n");
        }

        // add footer lines
        result.append("Amount owed is ").append(getTotalCharge()).append("\n");
        result.append("You earned ").append(getTotalFrequentRenterPoints()).append(" frequent renter points");
        return result.toString();
    }
```

函数htmlStatement() 则以HTML格式输出报表：
```
    public String htmlStatement() {
        // add header lines
        StringBuilder result = new StringBuilder("<H1>Rentals for <EM>" + getName() + "</EM></ H1><P>\n");

        // 详单细目
        for (Rental rental : _rentals) {
            // show figures for each rental
            result.append(rental.getMovie().getTitle()).append(": ").append(rental.getCharge()).append("<BR>\n");
        }

        // add footer lines
        result.append("<P>You owe <EM>").append(getTotalCharge()).append("</EM><P>\n");
        result.append("On this rental you earned <EM>").append(getTotalFrequentRenterPoints()).append("</EM> frequent renter points<P>");
        return result.toString();
    }
```


使⽤ Form Template Method（345）之前，我需要对上述两个函数做⼀些理，使它们成为同⼀个超类下的⼦类函数。
为了这⼀⽬的，我使⽤函数对象［Beck] 针对“报表打印”，创建⼀个独⽴的策略继承体系，如图11-1。

```plantuml
class Customer
class Statement
class TextStatement
class HtmlStatement

Customer .right.>"1" Statement
TextStatement -up-|> Statement
HtmlStatement -up-|> Statement
```

图11-1 针对“报表输出”使⽤Strategy模式

class Statement(){}
class TextStatement extend Statement(){}
class HtmlStatement extend Statement(){}

现在，通过Move Method （142），我将两个负责输出报表的函数分别搬移到对应的⼦类中：
```java
class Customer{
    public String statement(){
        return new TextStatement().value(this);
    }

    public String htmlStatement(){
        return new HtmlStatement().value(this);
    }
}

class TextStatement{
    public String value(Customer aCustomer){
        Enumeration rentals = aCustomer.getRentals();
        StringBuilder result = new StringBuilder("Rental Record for " + aCustomer.getName() + "\n");
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            // show figures for this rental
            result.append("\t" + each.getMovie().getTitle() + "\t"
                    + String.valueOf(each.getCharge()) + "\n");
        }
        // add footer lines
        result.append("Amount owed is ").append(String.valueOf(aCustomer.getTotalCharge())).append("\n");
        result.append("You earned ").append(String.valueOf(aCustomer.getTotalFrequentRenterPoints())).append(" frequent renter points");
        return result.toString();
    }
}

class HtmlStatement{
    public String value(Customer aCustomer){
        Enumeration rentals = aCustomer.getRentals();
        StringBuilder result = new StringBuilder("<H1>Rentals for <EM>" + aCustomer.getName() + "</EM></H1><P>\n");
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            // show figures for each rental
            result.append(each.getMovie().getTitle()).append(": ").append(String.valueOf(each.getCharge())).append("<BR>\n");
        }
        // add footer lines
        result.append("<P>You owe <EM>").append(String.valueOf(aCustomer.getTotalCharge())).append("</EM><P>\n");
        result.append("On this rental you earned <EM>").append(String.valueOf(aCustomer.getTotalFrequentRenterPoints())).append("</EM> frequent renter points<P>");
        return result.toString();
    }
}
```

搬移之后，我还对这两个函数的名称做了⼀些修改，使它们更好地适应 Strategy模式的要求。
我之所以为它们取相同名称，因为两者之间的差异不在于函数，⽽在于函数所属的类。
如果你想试着编译这段代码，还必须在Customer类中添加⼀个 getRentals() 函数，
并放宽 getTotalCharge()函数 和 getTotalFrequentRenterPoints()函数 的可⻅度。
⾯对两个⼦类中的相似函数，我可以开始实施 Form Template Method（345）了。
本重构的关键在于：运⽤Extract Method（110）将两个函数的不同部分提炼出来，从⽽将相似的代码和变动的代码分开。
每次提炼后，我就建⽴⼀个签名相同，但本体不同的函数。

第⼀个例⼦就是 打印报表表头。
上述两个函数都通过Customer对象获取信息， 但对运算结果字符串的格式化⽅式不同。
我可以将“对字符串的格式化”提炼到独⽴函数中，并将提炼所得命以相同的签名：
```java
class Statement{
    public String value(Customer aCustomer){
        Enumeration rentals = aCustomer.getRentals();
        String result = headerString(aCustomer);
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            // show figures for this rental
        }
        // add footer lines
        result += footerString(aCustomer);
        return result;
    }

    abstract String headerString(Customer aCustomer);
}

class TextStatement extends Statement{
    String headerString(Customer aCustomer) {
        return "Rental Record for " + aCustomer.getName() + "\n";
    }
    public String value(Customer aCustomer){
        Enumeration rentals = aCustomer.getRentals();
        
        String result = headerString(aCustomer);
        
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t"
                    + String.valueOf(each.getCharge()) + "\n";
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(aCustomer.getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(aCustomer.getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }
}

class HtmlStatement extends Statement{
    String headerString(Customer aCustomer) {
        return "<H1>Rentals for <EM>" + aCustomer.getName() + "</EM></H1><P>\n";
    }
    public String value(Customer aCustomer){
        Enumeration rentals = aCustomer.getRentals();
        
        String result = headerString(aCustomer);
        
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            // show figures for each rental
            result += each.getMovie().getTitle() + ": " + String.valueOf(each.getCharge()) + "<BR>\n";
        }
        // add footer lines
        result += "<P>You owe <EM>" + String.valueOf(aCustomer.getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" + String.valueOf(aCustomer.getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";
        return result;
    }
}

```

编译并测试，然后继续处理其他元素。
我将逐⼀对各个元素进⾏上述过程。
下⾯是整个重构完成后的结果：

```java
class TextStatement extends Statement{
    String headerString(Customer aCustomer) {
        return "Rental Record for " + aCustomer.getName() + "\n";
    }
    public String value(Customer aCustomer){
        Enumeration rentals = aCustomer.getRentals();
        
        String result = headerString(aCustomer);
        
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            // show figures for this rental
            result += eachRentalString(each);
        }
        // add footer lines
        result += footerString(aCustomer);
        return result;
    }

    String eachRentalString(Rental aRental){
        return "\t" + aRental.getMovie().getTitle() + "\t"
                + String.valueOf(aRental.getCharge()) + "\n";
    }

    String footerString(Customer aCustomer){
        return "Amount owed is " + String.valueOf(aCustomer.getTotalCharge()) + "\n"
                + "You earned " + String.valueOf(aCustomer.getTotalFrequentRenterPoints()) + " frequent renter points";
    }
}

class HtmlStatement extends Statement{
    String headerString(Customer aCustomer) {
        return "<H1>Rentals for <EM>" + aCustomer.getName() + "</EM></H1><P>\n";
    }
    public String value(Customer aCustomer){
        Enumeration rentals = aCustomer.getRentals();
        
        StringBuilder result = new StringBuilder(headerString(aCustomer));
        
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            // show figures for each rental
            result.append(eachRentalString(each));
        }
        // add footer lines
        result.append(footerString(aCustomer));
        return result.toString();
    }

    String eachRentalString(Rental aRental){
        return aRental.getMovie().getTitle() + ": " + String.valueOf(aRental.getCharge()) + "<BR>\n";
    }

    String footerString(Customer aCustomer){
        return "<P>You owe <EM>" + String.valueOf(aCustomer.getTotalCharge()) + "</EM><P>\n"
                + "On this rental you earned <EM>" + String.valueOf(aCustomer.getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";
    }
}
```

所有这些修改都完成后，两个value()函数 看上去已经⾮常相似了，
因此我可以使⽤ Pull up Method（322）将它们提升到超类中。
提升完毕后，我需要在超类中把⼦类函数声明为抽象函数。

class Statement...

public String value （Customer acustomer）｛

Enumeration rentals = aCustomer .getRentals（）； String result - headerstring （aCustomer）； while （rentals.hasMoreBlements（））｛

Rental each.= （Rental）rentals.nextBlement （）：

result += eachRentalString （each）；

feasze t- tootersering（acustomet） return result：

abstract String headerString （Customer aCustomer）； abstract String cachRentalstring（Rental aRenta1）； abstract String footerString （Customer aCustomer）；


然后我把 TextStatement.value()函数拿掉，编译并测试。
完成之后再把 HtmlStatement.value()也删掉，再次编译并测试。
最后结果如图11-2。

完成本重构后，处理其他种类的报表就容易多了：
你只需为statement再建⼀个⼦类，并在其中覆写3个抽象函数即可。


```plantuml
class Customer{
statement()
htmlStatement()
}

class Statement{
value(Customer)
headerString(Customer)
eachRentalString(Rental)
footerString(Customer)
}

class HtmlStatement{
headerString(Customer)
eachRentalString(Rental)
footerString(Customer)
}

class TextStatement{
headerString(Customer)
eachRentalString(Rental)
footerString(Customer)
}

Customer -right->"1" Statement
Statement <|-- HtmlStatement
Statement <|-- TextStatement
```

