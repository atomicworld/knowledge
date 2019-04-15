package org.atomic.java.catalog.class_.clone.deep;

import java.io.*;

public class TestSerial {
    public static void main(String[] args) throws Exception {
        Teacher teacher = new Teacher("Teacher Wang", 50);
        Student student1 = new Student("ZhangSan", 20, teacher);
        Student student2 = (Student) student1.deepClone();

        System.out.println("拷贝得到的信息:");
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println("---------------------------");

        // 将复制后的对象的老师信息修改一下：
        student2.getTeacher().setName("New Teacher Wang");
        student2.getTeacher().setAge(28);

        System.out.println("原来对象的教师：");
        System.out.println(student1.toString());
        System.out.println("---------------------------");

        System.out.println("修改了拷贝对象的教师后：");
        System.out.println("拷贝对象的教师：");
        System.out.println(student2.toString());

        // 由此证明序列化的方式实现了对象的深拷贝
    }

}

class Teacher implements Serializable {
    private String name;
    private int age;

    public Teacher() {
    }

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Student implements Serializable {
    private String name;
    private int age;
    private Teacher teacher;

    public Student() {
    }

    public Student(String name, int age, Teacher teacher) {
        this.name = name;
        this.age = age;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "姓名 = " +name+
                " ,年龄 = " +age+
                " ,老师姓名 = " + teacher.getName()+
                " ,老师年龄 = " + teacher.getAge();
    }

    public Object deepClone() throws Exception {
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}