package org.atomic.java.catalog.class_.clone.shallow;

public class Student implements Cloneable {

    private String name;
    private int length;
    private Age age;

    public Student(String name, Age age, int length) {
        this.name = name;
        this.age = age;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toString() {
        return "姓名是： " + this.getName() + "， 年龄为： " + this.getAge().toString() + ", 长度是： " + this.getLength();
    }

    /**
     * 重写Object类的clone方法
     */
    public Object clone() {
        Object obj = null;
        try {
            //调用Object类的clone方法，返回一个Object实例
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}