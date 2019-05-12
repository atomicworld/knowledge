package org.atomic.java.catalog.keywords.generic;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型测试, 类型相同");
        }

        System.out.println("==================================");

        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        Bucket<Integer> bucketInteger = new Bucket<Integer>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Bucket<String> bucketString = new Bucket<String>("key_value");

        System.out.println("泛型测试, key is " + bucketInteger.getT());
        System.out.println("泛型测试, key is " + bucketString.getT());
        System.out.println(Bucket.showKeyName(bucketInteger));
        System.out.println(Bucket.showKeyName(bucketString));

        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        Pair<Integer, String> p3 = new Pair<>(2, "pear");
        boolean same = GenericMethod1.compare(p1, p2);
        boolean same2 = GenericMethod1.compare(p2, p3);
        System.out.println("泛型方法, result = " + same);
        System.out.println("泛型方法, result = " + same2);

        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK

    }
}
