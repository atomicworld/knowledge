package org.atomic.java.catalog.class_.extend;

/**
 * 多态：
 * “ 继承、重写、向上转型：子类的引用赋给父类对象 ”
 * 有个好心人的解答：
 * 该问题的关键有两点：
 * 一是子类与父类的关系，二是重载方法的调用问题。
 * 子类对象可以直接当成父类对象使用，但反过来就不可以。
 *
 * 举例来说，人是父类，学生是人的子类，
 *  所以学生对象一定具备人对象的属性，但是人对象就未必具有学生对象的特性。
 *  所以学生对象可以当做人对象来使用，但是人对象就不能当做学生对象使用。
 *
 * 注意:当把子类对象当成父类对象使用时，子类对象将失去所有的子类特性，只保留与父类同名的属性和方法
 *      （同名方法不仅是函数名相同，而且参数类型也要一样，否则不予保留）。
 *
 * 一个类中如果定义了重载的方法，则系统在调用方法时，会根据参数的类型自动选择调用合适的方法。
 *
 */

class A {
    public String show(A obj) { return ("obj-A : param=obj-A"); }
    public String show(D obj) { return ("obj-A : param=obj-D"); }
}
class B extends A {
    public String show(A obj) { return ("obj-B : param=obj-A"); }
    public String show(B obj) { return ("obj-B : param=obj-B"); }
}
class C extends B{}
class D extends B{}
class Tutorials {
    public static void main(String [] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        System.out.println(a1.show(b));             //  "obj-A : param=obj-A"
        System.out.println(a1.show(c));             //  "obj-A : param=obj-A"
        System.out.println(a1.show(d));             //  "obj-A : param=obj-D"

        System.out.println(a2.show(b));             //  "obj-B : param=obj-A"
        System.out.println(a2.show(c));             //  "obj-B : param=obj-A"
        System.out.println(a2.show(d));             //  "obj-A : param=obj-D"

        System.out.println(b.show(b));              //  "obj-B : param=obj-B"
        System.out.println(b.show(c));              //  "obj-B : param=obj-B"
        System.out.println(b.show(d));              //  "obj-A : param=obj-D"
    }
}

/**
 *
 *  1) a1.shows(b),在A中没有含有B类参数的方法，但是含有A类参数的方法，根据子类对象父类可用的原则，所以调用方法
 *       public String show(A obj) { return ("obj-A : param=obj-A"); }
 *  2) a1.show(c），C类是B类的子类，而B类又是A类的子类，所以C类对象可以当制作A类对象使用。结果同上。
 *
 *  3) a1.show(d），根据参数类型直接调用A中的方法       public String show(D obj) { return ("obj-A : param=obj-D"); }
 *
 *  4) a2.show(b)，a2本来是一个B对象，但是将其赋给了A类变量，所以 a2 只保留了与 父类A 同名的属性和方法。
 *        a2.show(b)调用B类中的保留的与父类同名同参方法    public String show(A obj) { return ("obj-B : param=obj-A"); }
 *
 *  5) a2.show(c)，B类的保留方法中没有C类参数方法，但是有含有C的父类B的参数方法，所以调用的方法
 *       public String show(A obj) { return ("obj-B : param=obj-A"); }
 *    这样解释更合理：a2本来是类B的一个对象，但是又将值赋给了类A，C是B的子类，B是A的子类，因此a2保留了类B中与A同名的属性和方法。
 *
 *  6) a2.show(d)，调用的是A类中的;     public String show(D obj) { return ("obj-A : param=obj-D"); }
 *
 *  7) b.show(b），调用B类中的          public String show(B obj) { return ("obj-B : param=obj-B"); }
 *
 *  8) b.show(c)，B类中没有C类参数的方法，但是有B类参数的方法，所以调用方法     public String show(B obj) { return ("obj-B : param=obj-B"); }
 *
 *  9) b.show(d），解释同8
 *
 *  所以多态机制遵循的原则概括为：
 *      当超类对象引用变量 引用 子类对象时，被引用对象的类型 而不是引用变量的类型 决定了调用谁的成员方法，
 *      但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法，但是它仍然要根据继承链中方法调用的优先级来确认方法，
 *    该优先级为：
 *      this.show(O) > super.show(O) > this.show((super)O) > super.show((super)O)。
 *
 */
