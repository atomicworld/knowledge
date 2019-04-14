package org.atomic.java.catalog.classIntro.interface_abstract;

/**
 *  接口：（主要是用来 表示 “行为”，比如 ： 报警，独有的一些属性）
 *   接口中可以含有 变量和方法。
 *   但是要注意，
 *      接口中的
 *      变量会被隐式地指定为 public static final变量（并且只能是public static final变量，用private修饰会报编译错误），
 *      方法会被隐式地指定为 public abstract 方法且只能是 public abstract 方法（
 *      用其他关键字，比如private、protected、static、 final等修饰会报编译错误），并且接口中所有的方法不能有具体的实现，
 *      也就是说，接口中的方法必须都是抽象方法。
 *
 *      从这里可以隐约看出接口和抽象类的区别，接口是一种极度抽象的类型，它比抽象类更加 “抽象”，并且一般情况下不在接口中定义变量。
 *
 *
 */
public interface InterfaceDemo {

    //不能有静态代码块
//    {
//        System.out.println("hello, interface");
//    }

    public static final String var="hello interface";   // 成员变量，会被默认指定（public static final） 这里都是灰色的

//    private static final String var2="no, interface"; //报错
//    protected final String var3="no, interface";      //报错

//    private String var4="hello, interface";           // 不能定义非 public static final 的成员变量

    void demo();
    public abstract void demo1();
//    protected abstract void demo2();      // 不能是protected
//    private abstract void demo3();        // 不能是private

//    private String demo4(){}              //不能是成员方法

}
