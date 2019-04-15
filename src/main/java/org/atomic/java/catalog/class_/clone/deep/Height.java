package org.atomic.java.catalog.class_.clone.deep;

public class Height implements Cloneable {

    private int height;

    public Height(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String toString() {
        return this.height + "";
    }

    //重写Object的clone方法
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
