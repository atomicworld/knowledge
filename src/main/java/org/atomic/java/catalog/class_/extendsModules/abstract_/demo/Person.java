package org.atomic.java.catalog.class_.extendsModules.abstract_.demo;

public abstract class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void work();
}