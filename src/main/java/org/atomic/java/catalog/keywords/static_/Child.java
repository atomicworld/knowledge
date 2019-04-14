package org.atomic.java.catalog.keywords.static_;

public class Child extends Parent {

    static String childName = "hello";

    {
        System.out.println("child block");
    }

    static {
        System.out.println("child static block");
    }

    public Child() {
        System.out.println("child constructor");
    }
}