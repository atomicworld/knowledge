package org.atomic.java.catalog.class_.clone;

public class ObjRef {
    Obj obj = new Obj();
    int aInt = 11;

    public void changeObj(Obj inObj) {
        inObj.str = "changed value";
    }

    public void changePri(int inInt) {
        inInt = 22;
    }
}
