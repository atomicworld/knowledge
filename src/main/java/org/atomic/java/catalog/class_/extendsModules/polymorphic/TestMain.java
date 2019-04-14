package org.atomic.java.catalog.class_.extendsModules.polymorphic;

/**
 * 多态机制遵循的原则概括为：
 * 如果能够在子类找到方法，就直接使用，不能够找到，就去父类中寻找方法
 * this.show(O) > super.show(O) > this.show((super)O) > super.show((super)O)
 */
public class TestMain {
    public static void main(String[] args) {

        ParentA parentA1 = new ParentA();
        ParentA parentA2 = new ChildB_A();
        ChildB_A childB_a = new ChildB_A();
        ChildC_B childC_b = new ChildC_B();
        ChildD_B childD_b = new ChildD_B();

        System.out.println(parentA1.show(childB_a));
        /**
         * 在ParentA中没有含有ChildB_A类参数的方法，但是含有ParentA类参数的方法，
         * 根据子类对象父类可用的原则，所以调用方法
         * "ParentA-show-method : param = parentA"
         */

        System.out.println(parentA1.show(childC_b));
        /**
         * ChildC_B类是ChildB_A类的子类，而ChildB_A类又是ParentA类的子类，
         * 所以ChildC_B类对象可以当制作ParentA类对象使用，子类对象父类可用原则
         * "ParentA-show-method : param = parentA"
         */

        System.out.println(parentA1.show(childD_b));
        /**
         * 根据参数类型,直接调用ParentA中的方法
         * "ParentA-show-method : param = childD_b"
         */

        System.out.println(parentA2.show(childB_a));
        /**
         * a2本来是一个ChildB_A对象，但是将其赋给了A类变量，
         * 所以a2只保留了与 父类A 同名的属性和方法
         * "ChildB_A-show-Method : param = parentA"
         */
        System.out.println(parentA2.show(childC_b));
        /**
         * ChildB_A类的保留方法中没有C类参数方法，但是有含有C的父类B的参数方法，所以调用的方法
         * "ChildB_A-show-Method : param = parentA"
         */

        System.out.println(parentA2.show(childD_b));
        /**
         * 调用的是ParentA类中的
         * ParentA-show-method : param = childD_b
         */

        System.out.println(childB_a.show(childB_a));
        /**
         * 调用ChildB_A类中的
         * ChildB_A-show-Method : param = childB_a
         */

        System.out.println(childB_a.show(childC_b));
        /**
         * ChildB_A类中没有ChildC_B类参数的方法，但是有ChildB_A类参数的方法
         * ChildB_A-show-Method : param = childB_a
         */

        System.out.println(childB_a.show(childD_b));
        /**
         * ChildB_A类中没有ChildD_B类参数的方法，但是ParentA类中有参数ChildD_B的方法
         * ParentA-show-method : param = childD_b
         */
    }
}
