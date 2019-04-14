package org.atomic.java.catalog.class_.extendsModules.abstract_.demo;

public class TestMain {
    public static void main(String[] args) {
        Cooker cook = new Cooker();
        cook.setName("Jack");
        cook.work();

        Teacher teacher = new Teacher();
        teacher.setName("Tom");
        teacher.work();
    }
}
