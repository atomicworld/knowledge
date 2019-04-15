### 内部类

[TOC]

#### 定义

	在Java中，可以将一个类定义在另一个类里面或者一个方法里面，这样的类称为内部类

	如同一个人是由大脑、肢体、器官等身体结果组成，而内部类相当于其中的某个器官之一，例如心脏：它也有自己的属性和行为（血液、跳动）- 摘自菜鸟编程技术



#### 分类：

1. 成员内部类
2. 局部内部类
3. 匿名内部类
4. 静态内部类

##### 成员内部类

	成员内部类是最普通的内部类，它的定义为位于另一个类的内部

```
class Circle {
    double radius = 0;
    public static int count = 1;
    
    public Circle(double radius) {
        this.radius = radius;
        getDrawInstance().drawSahpe();   //必须先创建成员内部类的对象，再进行访问
    }

    private Draw getDrawInstance() {
        return new Draw();
    }
    
    class Draw {     //内部类
        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
            System.out.println(count);   //外部类的静态成员
        }
    }
}
```

	这样看起来，类Draw像是类Circle的一个成员，Circle称为外部类。

	**成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）**

	当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即**默认情况下访问的是成员内部类的成员**。如果要访问外部类的同名成员，需要以下面的形式进行访问：

```
外部类.this.成员变量
外部类.this.成员方法
```

	虽然成员内部类可以无条件地访问外部类的成员，而**外部类想访问成员内部类的成员却不是这么随心所欲**了。在外部类中如果要访问成员内部类的成员，必须**先创建一个成员内部类的对象，再通过指向这个对象的引用**来访问.

	**成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象**

```
Circle circle = new Circle();
//第一种方式：
Circle.Draw draw = circle.new Draw();  //必须通过 Circle 对象来创建
        
//第二种方式：
Circle.Inner inner1 = circle.getDrawInstance();
```

内部类可以拥有修饰符：

	private访问权限：如果成员内部类Inner用private修饰，则**只能在外部类的内部访问**

	protected访问权限：如果用protected修饰，则**只能在同一个包下或者继承外部类的情况下访问**

	public访问权限：如果用public修饰，则**任何地方都能访问**

	包访问权限：如果是默认访问权限，则**只能在同一个包下访问**

这一点和外部类有一点不一样，外部类只能被public和包访问两种权限修饰



##### 局部内部类

	局部内部类是**定义在一个方法或者一个作用域里面的类**，它和成员内部类的区别在于**局部内部类的访问仅限于方法内或者该作用域内**

```
class People{
    public People() {}
}
 
class Man{
    public Man(){}
     
    public People getWoman(){
        class Woman extends People{   //局部内部类
            int age =0;
        }
        return new Woman();
    }
}
```

	局部内部类就像是方法里面的一个局部变量一样，是**不能有public、protected、private以及static修饰符**的



#####匿名内部类

	匿名内部类应该是**平时我们编写代码时用得最多的**，在编写**事件监听**的代码时使用匿名内部类，不但方便，而且使代码更加容易维护

```
scan_bt.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
});
      
history_bt.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
});
```

	代码中需要给按钮设置监听器对象，使用匿名内部类能够在实现父类或者接口中的方法情况下同时产生一个相应的对象，但是前提是这个父类或者接口必须先存在才能这样使用。

	**匿名内部类是唯一一种没有构造器的类**。正因为其没有构造器，所以匿名内部类的使用范围非常有限，**大部分匿名内部类用于接口回调**。匿名内部类在编译的时候由系统自动起名为Outter$1.class。一般来说，匿名内部类用于继承其他类或是实现接口，并不需要增加额外的方法，**只是对继承方法的实现或是重写**。



##### 静态内部类

```
public class Test {
    public static void main(String[] args)  {
        Outter.Inner inner = new Outter.Inner();
    }
}
 
class Outter {
    public Outter() {}
     
    static class Inner {
        public Inner() {}
    }
}
```



	静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个**关键字static**。静态内部类是**不需要依赖于外部类的，这点和类的静态成员属性**有点类似，并且它**不能使用外部类的非static成员变量或者方法**，这点很好理解，因为在没有外部类的对象的情况下，可以创建静态内部类的对象，如果允许访问外部类的非static成员就会产生矛盾，因为外部类的非static成员必须依附于具体的对象。



#### 问题：

1. **为什么成员内部类可以无条件访问外部类的成员？**

　　在此之前，我们已经讨论过了成员内部类可以无条件访问外部类的成员，那具体究竟是如何实现的呢？

	https://www.cnblogs.com/dolphin0520/p/3811445.html 字节码文件分析，

```
final com.cxh.test2.Outter this$0;
```

　　这行是一个指向外部类对象的指针，看到这里想必大家豁然开朗了。

	也就是说**编译器会默认为成员内部类添加了一个指向外部类对象的引用**，那么这个引用是如何赋初值的呢？下面接着看内部类的构造器：

```
public com.cxh.test2.Outter$Inner(com.cxh.test2.Outter);
```

	虽然我们在定义的内部类的构造器是无参构造器，编译器还是会默认添加一个参数，该参数的类型为指向外部类对象的一个引用，所以成员内部类中的Outter this&0 指针便指向了外部类对象，因此可以在成员内部类中随意访问外部类的成员。从这里也间接说明了成员内部类是依赖于外部类的，如果没有创建外部类的对象，则无法对Outter this&0引用进行初始化赋值，也就无法创建成员内部类的对象了



2. **为什么局部内部类和匿名内部类只能访问局部final变量？**

   如果局部变量的值在编译期间就可以确定，则直接在匿名内部里面创建一个拷贝。

   如果局部变量的值无法在编译期间确定，则通过构造器传参的方式来对拷贝进行初始化赋值

   防止局部变量在 内部类运行的时候被篡改，所以要采用final修饰



3. **静态内部类有特殊的地方吗？**

   态内部类是不依赖于外部类的，也就说可以在不创建外部类对象的情况下创建内部类的对象

   另外，静态内部类是不持有指向外部类对象的引用的



#### 内部类的使用场景和好处：

1. 每个内部类都能独立的继承一个接口的实现，所以无论外部类是否已经继承了某个(接口的)实现，对于内部类都没有影响

2. 方便将存在一定逻辑关系的类组织在一起，又可以对外界隐藏

3. 方便编写事件驱动程序
4. 方便编写线程代码

内部类的存在使得Java的多继承机制变得更加完善



##### 常见的与内部类相关的笔试面试题：

1.根据注释填写(1)，(2)，(3)处的代码

```
public class Test{
    public static void main(String[] args){
           // 初始化Bean1
           (1)
           bean1.I++;
           // 初始化Bean2
           (2)
           bean2.J++;
           //初始化Bean3
           (3)
           bean3.k++;
    }
    class Bean1{
           public int I = 0;
    }
 
    static class Bean2{
           public int J = 0;
    }
}
 
class Bean{
    class Bean3{
           public int k = 0;
    }
}
```

答案：

1. 创建静态内部类对象的一般形式为：  外部类类名.内部类类名 xxx = new 外部类类名.内部类类名()

2. 创建成员内部类对象的一般形式为：  外部类类名.内部类类名 xxx = 外部类对象名.new 内部类类名()

   （1）创建成员内部类 

   	Test test = new Test ();

   	Test.Bean1 bean1 = test .new Bean1();

   （2）创建静态内部类 

   	Test.Bean2  bean2 = new Test.Bean2();

   （3）创建成员内部类 

   	Bean bean = new Bean();      

   	Bean.Bean3  bean3 =  bean.new Bean3();   


2.下面这段代码的输出结果是什么？

```
public class Test {
    public static void main(String[] args)  {
        Outter outter = new Outter();
        outter.new Inner().print();
    }
}

class Outter{
    private int a = 1;
    class Inner {
        private int a = 2;
        public void print() {
            int a = 3;
            System.out.println("局部变量：" + a);	//3
            System.out.println("内部类变量：" + this.a);	//2
            System.out.println("外部类变量：" + Outter.this.a); //1
        }
    }
}
```



最后补充一点知识：**关于成员内部类的继承问题**。

	一般来说，内部类是很少用来作为继承用的。但是当用来继承的话，要注意两点：

　　1）成员内部类的引用方式必须为 Outter.Inner.

　　2）构造器中必须有指向外部类对象的引用，并通过这个引用调用super()。这段代码摘自《Java编程思想》

```
class WithInner {
    class Inner{}
}

class InheritInner extends WithInner.Inner {
      
    // InheritInner() 是不能通过编译的，一定要加上形参
    InheritInner(WithInner wi) {
        wi.super(); //必须有这句调用
    }
  
    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner obj = new InheritInner(wi);
    }
}
```

