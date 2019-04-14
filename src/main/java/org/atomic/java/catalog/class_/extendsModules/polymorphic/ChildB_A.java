package org.atomic.java.catalog.class_.extendsModules.polymorphic;

public class ChildB_A extends ParentA {

    public String show(ParentA parentA) {
        return ("ChildB_A-show-Method : param = parentA");
    }

    public String show(ChildB_A childB_a) {
        return ("ChildB_A-show-Method : param = childB_a");
    }

}
