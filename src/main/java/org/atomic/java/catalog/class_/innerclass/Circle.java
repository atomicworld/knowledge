package org.atomic.java.catalog.class_.innerclass;

public class Circle {

    double radius = 0;
    public static int count = 1;

    public Circle(double radius) {
        this.radius = radius;
        getDrawInstance().drawSahpe();   //必须先创建成员内部类的对象，再进行访问
    }

    private Draw getDrawInstance() {
        return new Draw();
    }

    class Draw {
        // 成员内部类
        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
            System.out.println(count);   //外部类的静态成员
        }
    }
}
