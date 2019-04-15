package org.atomic.java.catalog.class_.clone.deep;

public class TestClone {

    public static void main(String[] args) {
        Height height = new Height(80);

        Kid kid1 = new Kid("Jim", height, 105);
        Kid kid2 = (Kid) kid1.clone();

        System.out.println(kid1.toString());
        System.out.println(kid2.toString());
        System.out.println();

        kid1.setName("Baby");
        height.setHeight(90);
        // kid1.setHeight(new Height(90));
        // 使用这种方式修改age属性值的话，stu2是不会跟着改变的。
        // 因为创建了一个新的Age类对象而不是改变原对象的实例值
        kid1.setLength(216);
        System.out.println(kid1.toString());
        System.out.println(kid2.toString());
    }

}
