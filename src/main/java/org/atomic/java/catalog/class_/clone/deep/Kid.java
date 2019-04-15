package org.atomic.java.catalog.class_.clone.deep;

public class Kid implements Cloneable {

    private String name;
    private Height height;
    private int length;

    //构造方法,其中一个参数为另一个类的对象
    public Kid(String name, Height height, int length) {
        this.name = name;
        this.height = height;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toString() {
        return "姓名是： " + this.getName() +
                "，身高： " + this.getHeight().toString() +
                ", 长度是： " + this.getLength();
    }

    public Object clone() {
        Object obj = null;
        try {
            //调用Object类的clone方法——浅拷贝
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // TODO 关注点
        Kid kid = (Kid) obj;
        kid.height = (Height) kid.getHeight().clone();
        return obj;
    }
}