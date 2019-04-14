package org.atomic.java.catalog.keyWords;

/**
 *  static
 *
 *  parent静态代码块 > child静态代码块 >  parent非静态代码块 > parent构造方法 > child非静态代码块 > child构造方法
 *
 *  1.在静态方法里只能直接调用同类中其他的静态成员（包括变量和方法），而不能直接访问类中的非静态成员。
 *  这是因为: 对于非静态的方法和变量，需要先创建类的实例对象后才可使用，而静态方法在使用前不用创建任何对象。并且静态方法无法引用 this 关键字。
 *
 *  2.一个类可以使用不包含在任何方法体中的静态代码块，当类被载入时，静态代码块被执行，且只被执行一次，静态块常用来执行类属性的初始化。
 *
 *  三个步骤来完成：装载、链接和初始化; 对象的初始化顺序:
 *
 *  马上对其进行初始化工作,其实就是3种情况：
 *  　 a .用new实例化一个类时
 *     b.读取或者设置类的静态字段时（不包括被final修饰的静态字段，因为他们已经被塞进常量池了）
 *          特殊情况：public static final int A = 4 + new Random().nextInt(10);//静态final变量在编译时不定的情况
 *     c.以及执行静态方法的时候。
 *
 */
    class Parent{
        static String name = "hello";
        { System.out.println("parent block"); }
        static { System.out.println("parent static block"); }
        public Parent(){
            System.out.println("parent constructor");
        }
    }

    class Child extends Parent{
        static String childName = "hello";
        { System.out.println("child block"); }
        static { System.out.println("child static block"); }
        public Child(){
            System.out.println("child constructor");
        }
    }

    public class StaticInit {
        public static int a;//静态变量，初始化为0

       public static void main(String[] args) {
            new Child();
           System.out.println(a++);
        }
    }
