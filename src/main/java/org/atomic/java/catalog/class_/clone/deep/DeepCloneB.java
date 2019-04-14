package org.atomic.java.catalog.class_.clone.deep;

public class DeepCloneB {

    public int aInt;
    public UnDeepCloneA unCA = new UnDeepCloneA(111);

    public Object clone() {
        DeepCloneB o = null;
        try {
            o = (DeepCloneB) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        o.unCA = (UnDeepCloneA) unCA.clone();

        return o;
    }
}
