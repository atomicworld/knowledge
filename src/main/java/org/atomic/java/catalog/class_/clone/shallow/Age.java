package org.atomic.java.catalog.class_.clone.shallow;

public class Age {

    private int age;

    public Age(int age) {
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String toString() {
        return getAge() + "";
    }

}