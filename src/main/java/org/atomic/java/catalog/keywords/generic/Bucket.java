package org.atomic.java.catalog.keywords.generic;

public class Bucket<T> {

    public Bucket() {

    }

    public Bucket(T t) {
        this.t = t;
    }

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static <T> T showKeyName(Bucket<T> bucket){
        System.out.println("container key :" + bucket.getT());
        T test = bucket.getT();
        return test;
    }

}
