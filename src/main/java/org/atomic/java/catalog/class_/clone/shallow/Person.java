package org.atomic.java.catalog.class_.clone.shallow;

public class Person {

    private Age age;
    private String name;

    public Person(Age age, String name) {
        this.age = age;
        this.name = name;
    }

    //拷贝构造方法
    public Person(Person p) {
        this.name = p.name;
        this.age = p.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name + " " + this.age;
    }
}