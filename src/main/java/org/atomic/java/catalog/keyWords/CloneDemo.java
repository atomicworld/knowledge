package org.atomic.java.catalog.keyWords;

/**
 *  Java的所有类都默认继承java.lang.Object类，在java.lang.Object类中有一个方法clone().
 *
 *  一是拷贝对象返回的是一个新对象，而不是一个引用。
 *  二是拷贝对象与用 new操作符返回的新对象的区别: 这个拷贝已经包含了一些原来对象的信息，而不是对象的初始信息。
 *
 *
 */

class Obj {
    String str = "init value";
    public String toString() { return str; }
}
class ObjRef {
    Obj obj = new Obj();
    int aInt = 11;
    public void changeObj(Obj inObj) {
        inObj.str = "changed value";
    }
    public void changePri(int inInt) {
        inInt = 22;
    }
}

public class CloneDemo implements Cloneable {

    public Object clone() {
        CloneDemo cloneDemo = null;
        try {
            cloneDemo = (CloneDemo) super.clone();   //super.clone()直接或 间接调用了java.lang.Object类的clone()方法
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneDemo;
    }

    public static void main(String[] args) {
        ObjRef objRef = new ObjRef();

        System.out.println("Before call changeObj() method: " + objRef.obj);
        objRef.changeObj(objRef.obj);
        System.out.println("After call changeObj() method: " + objRef.obj);

        System.out.println("==================Print Primtive=================");
        System.out.println("Before call changePri() method: " + objRef.aInt);
        objRef.changePri(objRef.aInt);
        System.out.println("After call changePri() method: " + objRef.aInt);

    }


}
