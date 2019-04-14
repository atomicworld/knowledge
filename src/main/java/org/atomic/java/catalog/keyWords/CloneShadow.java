package org.atomic.java.catalog.keyWords;

/**
 * 影子clone: 只能是克隆了 对象的引用， 但是指向的对象还是同一个，会有影响
 */

class UnCloneA {
    private int i;
    public UnCloneA(int ii) { i = ii; }
    public void doubleValue() { i *= 2; }
    public String toString() {
        return Integer.toString(i);
    }

    public Object clone(){
        UnCloneA o = null;
        try{
            o = (UnCloneA)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return o;
    }

}

class CloneB implements Cloneable{
    public int aInt;
    public UnCloneA unCA = new UnCloneA(111);
    public Object clone(){
        CloneB o = null;
        try{
            o = (CloneB)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }

        o.unCA = (UnCloneA)unCA.clone();

        return o;
    }
}

public class CloneShadow {
    public static void main(String[] a){

        CloneB b1 = new CloneB();
        b1.aInt = 11;
        System.out.println("before clone,b1.aInt = "+ b1.aInt);
        System.out.println("before clone,b1.unCA = "+ b1.unCA);

        CloneB b2 = (CloneB)b1.clone();
        b2.aInt = 22;
        b2.unCA.doubleValue();
        System.out.println("=================================");
        System.out.println("after clone,b1.aInt = "+ b1.aInt);
        System.out.println("after clone,b1.unCA = "+ b1.unCA);
        System.out.println("=================================");
        System.out.println("after clone,b2.aInt = "+ b2.aInt);
        System.out.println("after clone,b2.unCA = "+ b2.unCA);
    }

    /**
     * 输出的结果说明:
     *    int类型的变量aInt 和 UnCloneA的实例对象unCA的 clone结果不一致，
     *      int类型是真正的被clone了，因为改变了b2中的aInt变量，对b1的aInt没有产生影响，
     *      也就是说，b2.aInt与b1.aInt已经占据了不同的内存空间，b2.aInt是b1.aInt的一个真正拷贝。
     *    相反，对b2.unCA的改变同时改变了b1.unCA，很明显，b2.unCA和b1.unCA是仅仅指向同一个对象的不同引用！
     *    从中可以看出，调用Object类中clone()方法产生的效果是：
     *      先在内存中开辟一块和原始对象一样的空间，然后原样拷贝原始对象中的内容。
     *      对基本数据类型，这样的操作是没有问题的，
     *      但对非基本类型变量，我们知道它们保存的仅仅是对象的引用，
     *      这也导致clone后的非基本类型变量和原始对象中相应的变量指向的是同一个对象。
     *
     *  大多时候，这种clone的结果往往不是我们所希望的结果，这种clone也被称为"影子clone"。
     *
     *  要想让b2.unCA指向与b2.unCA不同的对象，而且b2.unCA中还要包含b1.unCA中的信息作为初始信息，就要实现深度clone。
     *
     *
     */

}

