package org.atomic.java.catalog.class_.interface_abstract;


public class AbstractExample {

//    abstract class People {
//        private String name;
//        public String getName() { return name; }
//        public void setName(String name) { this.name = name; }
//
//        public abstract void work();
//    }
//
//    class Teacher extends People {
//        public void work(){
//            System.out.println("my work is teaching students.");
//        }
//    }
//
//    class Cook extends People {
//        public void work(){
//            System.out.println("my work is cooking foods.");
//        }
//    }

    public static void main(String[] args) {
        Cook cook=new Cook();
        cook.setName("Jack");
        cook.work();

        Teacher teacher=new Teacher();
        teacher.setName("Tom");
        teacher.work();
    }

}

abstract class People {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public abstract void work();
}

class Teacher extends People {
    public void work(){
        System.out.println(this.getName()+"'s work is teaching students.");
    }
}

class Cook extends People {
    public void work(){
        System.out.println(this.getName()+"'s work is cooking foods.");
    }
}
