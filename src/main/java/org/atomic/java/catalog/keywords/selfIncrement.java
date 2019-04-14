package org.atomic.java.catalog.keywords;

/**
 * 关于自增、自减 对于变量作用域的影响
 */
public class selfIncrement {
    public static void main(String[] args) {

        int j = 0;
        int k = 100;
        for (int i = 0; i < 100; i++) {
            j = j++;
//            j=++j;
//        k=k--;
            k = --k;
            System.out.println("j= " + j);
            System.out.println("k= " + k);
        }
    }
}
