package org.atomic.java.catalog.class_.clone.deep;

/**
 * 深度clone:
 *
 */
public class DeepClone {
    public static void main(String[] a) {

        DeepCloneB b1 = new DeepCloneB();
        b1.aInt = 11;
        System.out.println("before clone,b1.aInt = " + b1.aInt);
        System.out.println("before clone,b1.unCA = " + b1.unCA);

        DeepCloneB b2 = (DeepCloneB) b1.clone();
        b2.aInt = 22;
        b2.unCA.doubleValue();
        System.out.println("=================================");
        System.out.println("after clone,b1.aInt = " + b1.aInt);
        System.out.println("after clone,b1.unCA = " + b1.unCA);
        System.out.println("=================================");
        System.out.println("after clone,b2.aInt = " + b2.aInt);
        System.out.println("after clone,b2.unCA = " + b2.unCA);
    }
}

/**
 * 输出的结果说明:
 *   现在b2.unCA的改变对b1.unCA没有产生影响。
 *   此时b1.unCA与b2.unCA指向了两个不同的UnCloneA实例，
 *   而且在 CloneB_Deep b2 = (CloneB_Deep)b1.clone();
 *   调用的那一刻 b1和b2 拥有相同的值，在这里，b1.i = b2.i = 11。
 *
 *   要知道不是所有的类都能实现深度clone的。
 *       例如，如果把上面的 CloneB_Deep 类中的 UnCloneA_Deep 类型变量改成StringBuffer类型，
 *       看一下JDK API中关于StringBuffer的说明，StringBuffer没有重载clone()方法，
 *       更为严重的是StringBuffer还是一个final类，这也是说我们也不能用继承的办法间接实现StringBuffer的clone。
 *
 *       如果一个类中包含有StringBuffer类型对象或和 StringBuffer相似类的对象，
 *       我们有两种选择：要么只能实现影子clone，要么就在类的clone()方法中加一句
 *       （假设是SringBuffer对象，而且变量名仍是unCA）： o.unCA = new StringBuffer(unCA.toString()); //原来的是：o.unCA = (UnCloneA)unCA.clone();
 *
 *
 *    还要知道的是除了
 *      基本数据类型能自动实现深度clone以外，String对象是一个例外，
 *      它clone后的表现好象也实现了深度clone，虽然这只是一个假象，但却大大方便了我们的编程。(String 是不可以更改的，会new出新对象)
 *
 */
