package org.atomic.java.catalog.classObj.interface_abstract;

/**
 * 抽象类： （主要是用来 表示 “属性”，让子类去继承，也就具备了这种属性）
 *  抽象方法必须用abstract关键字进行修饰。
 *  如果一个类含有抽象方法，则称这个类为抽象类，抽象类必须在类前用abstract关键字修饰。
 *  因为抽象类中含有无具体实现的方法，所以不能用抽象类创建对象。
 *
 *  抽象类就是为了继承而存在的，如果你定义了一个抽象类，却不去继承它，
 *  那么等于白白创建了这个抽象类，因为你不能用它来做任何事情。
 *
 *  对于一个父类，如果它的某个方法在父类中实现出来没有任何意义，必须根据子类的实际需
 *  求来进行不同的实现，那么就可以将这个方法声明为abstract方法，此时这个类也就成为abstract类了。
 *
 *
 *  包含抽象方法的类称为抽象类，但并不意味着抽象类中只能有抽象方法，
 *  它和普通类一样，同样可以拥有成员变量和普通的成员方法。
 *
 *  注意，抽象类和普通类的主要有三点区别：
 *　　1）抽象方法必须为public或者protected（因为如果为private，则不能被子类继承，子类便无法实现该方法），缺省情况下默认为public。
 *　　2）抽象类不能用来创建对象；
 *　　3）如果一个类继承于一个抽象类，则子类必须实现父类的抽象方法。如果子类没有实现父类的抽象方法，则必须将子类也定义为为abstract类。
 *
 */

public abstract class AbstractDemo {    //abstract修饰， 可以防止被实例化
//public class AbstractDemo {           //这样也是抽象类，可以被实例化

    //可以有静态代码块
    {
        System.out.println("hello, interface");
    }

    private String var="hello";             //可以存在 一般的成员变量
    public static final String var2="test"; //可以存在 静态不可修改的成员变量

    public void demo(){                     //可以存在 一般的方法
        System.out.println("hello world!");
    }

    public abstract void demo2();           //可以存在 public 的抽象方法
    protected abstract void demo3();        //可以存在 protected 的抽象方法，因为可以给子类继承。（extends）

    protected void demo4(){}        //可以存在 protected 的方法，因为可以给子类继承。（extends）

    public static void main(String[] args) {
        AbstractDemo abstractDemo1 = new AbstractDemo() {
            @Override
            public void demo2() {}
            @Override
            protected void demo3() { }
        };//()

//        AbstractDemo abstractDemo1 = new AbstractDemo();    //没法直接实例化（abstract）
    }

}
