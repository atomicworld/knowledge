package org.atomic.java;

public class TestMain {

    public static void main(String[] args) {

        int a=10,b=15,temp;

        long start,end;
        start = System.currentTimeMillis();
        a = a^b;
        b = b^a;
        a = a^b;
        end = System.currentTimeMillis();

        System.out.println("^ cost time: "+(end-start) +"a、b =" +a+b);

        start = System.currentTimeMillis();
        temp = a;
        a = b;
        b =temp;
        end = System.currentTimeMillis();
        System.out.println("temp var time: "+(end-start) +"a、b =" +a+b);

    }
}
