package org.atomic.java.catalog.class_.clone.deep;

public class UnDeepCloneA {
    private int i;

    public UnDeepCloneA(int ii) {
        i = ii;
    }

    public void doubleValue() {
        i *= 2;
    }

    public String toString() {
        return Integer.toString(i);
    }

    public Object clone() {
        UnDeepCloneA o = null;
        try {
            o = (UnDeepCloneA) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
