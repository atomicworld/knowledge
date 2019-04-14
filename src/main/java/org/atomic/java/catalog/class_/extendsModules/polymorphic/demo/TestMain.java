package org.atomic.java.catalog.class_.extendsModules.polymorphic.demo;

public class TestMain {
    public static void main(String[] args) {

        Person person = new Student();

        Eat eat = new Bird();

        Animal animal = new Bird();

    }
}
