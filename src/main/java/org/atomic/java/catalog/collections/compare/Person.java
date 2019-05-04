package org.atomic.java.catalog.collections.compare;

public class Person implements Comparable<Person> {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }
}