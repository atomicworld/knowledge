package org.atomic.java.innerclass;

import org.atomic.java.catalog.class_.interface_abstract.InterfaceDemo;

/**
 * 关于内部类：
 *
 * （1）静态内部类：它相当于外部类的静态成员一样，使用static修饰的内部类，它隶属于外部类，使用起来相当于一个独立的外部类。
 * （2）成员内部类：它相当于外部类普通的成员一样，隶属于外部类的具体对象，在定义它的时候，需要先创建外部类对象，再创建它的实例。
 * （3）局部内部类：它定义在一个方法的方法体中，它往往仅作为方法短暂的使用，只能访问用final修饰的局部变量。
 * （4）匿名内部类：它也定义在方法体中，但是没有一个具体的名字，具有非常大的灵活性，工作本质与局部内部类类似。
 *
 *  成员式内部类分为：静态内部类和成员内部类。
 *  局部式内部类分为：普通局部内部类和匿名内部类
 *
 *  why use:
 *      一、内部类中定义的方法能访问到它所在外部类的私有属性及方法；
 *      二、外部类无法实现对同一包中的其他类隐藏，而内部类可以做到这一点；
 *      三、匿名内部类在我们只需使用该类的实例依次时可以有效减少我们的代码量。
 *
 *
 */
public class InnerClass {

    private int own = 1;
    private static int var = 2;

    /**
     * 非静态内部类 (持有外围类对象的引用 [隐式持有]),导致内存泄露（Memory Leak）的一个常见原因之一
     * 内部类需要等外部类创建了对象以后才会被加载到JVM中，它属于外部类的某个实例，因此它可以访问外部类的静态和非静态成员。
     * 创建成员内部类的时候，语法比较特殊，首先创建一个外部类的实例，然后用这个实例调用new语句，代码如下：
     *      public static void main(String[] args) {
                Outter o = new Outter();            //创建外部类实例
                Outter.Inner i = o.new Inner();     //创建内部类实例
            }
     */
    class Inner_1 {
        public void innerMethod() { // 不管是静态方法还是非静态方法都可以在非静态内部类中访问
            System.out.println("The var own in Outer is " + own);   //内部类访问外围类私有变量
            System.out.println("The var own in Outer is " + InnerClass.this.own);   //内部类访问外围类私有变量
        }
    }


    /**
     *   静态内部类相对于外部类来说，仅仅是包含关系，缩小了命名空间，完整的类名中多了一个外部类的名称。
     *   本质上是独立的两个类，JVM也不知道他们两个有包含关系。
     */
    static class Inner_2 {   // 静态内部类只能访问外部类的静态成员
        public void showMessage() {
//            System.out.println("Message from nested static class: " + msg); //试着访问：非静态的成员msg，这将导致编译错误
            System.out.println("Message from nested static class: " + var); //可以访问静态成员 var
        }
    }


    //*************************局部内部类*******//
    public void outerMethod() {

        System.out.println("In Outer class");
        Inner_1 inner = new Inner_1();
        inner.innerMethod();

        /**
         * 普通局部内部类:
         *  它不能使用static关键字，只能使用final、abstract关键字，
         *  仅可以访问外部类带有final关键字的局部变量，因为它访问的是一个字面量或镜像，该局部变量已经不存在了。（一致性的考虑。）
         *  但是，它可以任意的访问外部类对象的成员变量。
         *
         * 说明：
         *  与成员内部类类似，局部内部类的构造方法也会自动加上一个外部类型的参数，以及为该内部类加一个外部类型的成员变量。
         *  当局部内部类
         *      定义在静态方法中时，就相当于静态内部类：
         *      定义在普通的成员方法中时，则相当于成员内部类。
         */
        class Inner_3 {
            String b="yes";
            public void innerMethod() {
                System.out.println("The var own in Outer is " + own);
                System.out.println("get the var b="+b);
            }
        }


        //实现了一个接口，必须实现其中的抽象方法。不然就得定义为抽象类
        /**
         * 匿名内部类是直接使用new来生成一个对象的引用。当然这个引用是隐式的
         *  其实，它的工作原理相当于局部内部类，只是没有一个具体的名字而已，外部也无法直接使用它。
         *  注意：匿名内部类编译后的class文件的命名是按照匿名内部类的排列顺序来进行的，直接在外部类后面加上“$”和序号，例如Outter$1.class。
         *  <p> 可以访问另一个包下的，类的 protected方法. 隐藏父类的方法吧 </p>
         *  <code>
         *      new testClass(){
         *          void callParentTest(){
         *              super.test();
     *              }
         *      }.callParentTest();
         *
         *  </code>
         */
        InterfaceDemo id = new InterfaceDemo() {
            @Override
            public void demo(){
                System.out.println("a no-name inner class.");
            }
            @Override
            public void demo1(){
                System.out.println("a no-name inner class.");
            }
        };
        //减少继承类的书写。
        id.demo();
        id.demo1();
        /**
         * 因为内部类，直接创建实例，并且只使用一次。
         * 比如 Runnable（）接口，重新定义run（）方法，这样new 出来的就相当于一个匿名的 类。
         */
    }



    public static void main(String[] args) {
        InnerClass outer = new InnerClass();
        outer.outerMethod();
    }

}
