package org.atomic.java.catalog.class_.clone;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 要想弄明白 克隆属性，还得先弄清楚， java中的指针。
 * <p>
 * 和C语言一样，"值传递"--基本数据类型    "引用传递"--对象类型，‘=’给对象赋值
 * 当把Java的基本数据类型（如int，char，double等）作为入口参数传给函数体的时候，传入的参数
 * 在函数体内部变成了局部变量，这个局部变量是输入参数的一个拷贝，所有的函数体内部的操作都是针
 * 对这个拷贝的操作，函数执行结束后，这个局部变量也就完成了它的使命，它影响不到作为输入参数的
 * 变量。这种方式的参数传递被称为"值传递"。
 * 而在Java中用对象的作为入口 参数的传递则缺省为"引用传递"，也就是说仅仅传递了对象的一个
 * "引用"，这个"引用"的概念同C语言中的指针引用是一样的。当函数体内部对输入变量改变时，实质
 * 上就是在对这个对象的直接操作。
 * <p>
 * 除了在函数传值的时候是"引用传递"，在任何用"＝"向对象变量赋值的时候都是"引用传递"。
 * <p>
 * 对Hashtable表存储的任何一个StringBuffer对象（更确切的说应该是对象的引用）的改动，
 * 实际上都是对同一个 "StringBuffer"的改动。
 * 所以 Hashtable并不能真正存储能对象，而只能存储对象的引用。
 * 也应该知道这条原则对与Hashtable相 似的Vector, List, Map, Set等都是一样的。
 */
public class CloneDemo1 {

    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        StringBuffer sb = new StringBuffer();
        sb.append("abc,");
        hashtable.put("1", sb);
        sb.append("def,");
        hashtable.put("2", sb);
        sb.append("mno,");
        hashtable.put("3", sb);
        sb.append("xyz.");
        hashtable.put("4", sb);

        int numObj = 0;
        Enumeration it = hashtable.elements();
        while (it.hasMoreElements()) {
            System.out.print("get StringBufffer " + (++numObj) + " from Hashtable: ");
            System.out.println(it.nextElement());
        }
    }
}
