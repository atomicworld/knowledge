package org.atomic.java.catalog.keyWords;

import java.lang.Class;

/**
 * 1.反射机制的概念:
 *    指在运行状态中,对于任意一个类,都能够知道这个类的所有属性和方法,对于任意一个对象,都能调用它的任意一个方法.
 * 2.反射机制的应用:
 *    生成动态代理,面向切片编程 (在调用方法的前后各加栈帧).
 * 3.反射机制的原理:
 *      一切皆对象----类也是对象.  类中的内容 :modifier  constructor  field  method.  是java.lang.class的对象.
 *
 *
 */

class Apple{
    private String name;

    public void setName(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void eat(){
        System.out.println("good fruit!");
    }
}

public class ReflectDemo {

    public static void main(String[] args) {
        try{
            //第一种方式：
            Class c1= Class.forName("org.atomic.java.catalog.keyWords.Apple");

            //第二种方式：(java中每个类型都有class 属性.)
            Class c2 = Apple.class;

            //第三种方式：(java语言中任何一个java对象都有getClass 方法)
            Apple a=new Apple();
            Class c3 = a.getClass(); //c3是运行时类 (e的运行时类是Employee)

            System.out.print("class c1.getName= "+c1.getName()+",c1.getType="+c1.getTypeParameters());
            System.out.print("class c2.getName= "+c2.getName()+",c2.getType="+c2.getTypeParameters());

            Object appleDemo = c1.newInstance(); //调用了Apple的无参数构造方法.

            System.out.println(c1.getMethod("getName"));
//            System.out.println(c1.getField());


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
