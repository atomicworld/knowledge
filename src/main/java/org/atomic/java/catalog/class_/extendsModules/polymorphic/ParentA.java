package org.atomic.java.catalog.class_.extendsModules.polymorphic;

public class ParentA {

    public String show(ParentA parentA) {
        return ("ParentA-show-method : param = parentA");
    }

    public String show(ChildD_B childD_b) {
        return ("ParentA-show-method : param = childD_b");
    }

}
