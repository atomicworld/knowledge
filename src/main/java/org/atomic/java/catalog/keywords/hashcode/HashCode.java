package org.atomic.java.catalog.keywords.hashcode;

/**
 * 关于hashcode
 */

public class HashCode {

    public static int hashCode(int value) { //value表示Integer对象所包装的整型值，所以Integer类的hashCode方法仅仅是简单的返回了自身的值
        return value;
    }

    /*public static int hashCode(double value) {
        long bits = doubleToLongBits(value);    //Double类的hashCode方法首先会将它的值转为long类型，然后返回低32位和高32位的"异或"的结果作为hashCode
        return (int)(bits ^ (bits >>> 32));

    }*/

    //一个直接的办法就是直接拿得到的hashCode除以capacity（桶的数量），然后用所得的余数作为桶号。
    // 不过在Java中，hashCode是int型的，而Java中的int型均为有符号，所以我们要是直接使用返回的hashCode的话可能会得到一个负数，显然桶号是不能为负的。
    // 所以我们先将返回的hashCode转变为一个非负整数，再用它除以capacity取余数，作为key的对应桶号：
    // M: 桶数、x：对象
    /*private int hash(K key) {
        return (x.hashCode() & 0x7fffffff) % M;
    }*/


    // 拉链法处理碰撞：
    //初始时所有链表均为空，当一个键被散列到一个桶时，这个键就成为相应桶中链表的首结点，
    //之后若再有一个键被散列到这个桶（即发生碰撞），第二个键就会成为链表的第二个结点，以此类推。
    // 这样一来，当桶数为M，散列表中存储的键值对数目为N时，平均每个桶中的链表包含的结点数为N / M。
    // 因此，当我们查找一个键时，首先通过散列函数确定它所在的桶，这一步所需时间为O(1)；
    // 然后我们依次比较桶中结点的键与给定键，若相等则找到了指定键值对，这一步所需时间为O(N / M)。
    // 所以查找操作所需的时间为O(N / M)，而通常我们都能够保证N是M的常数倍，所以散列表的查找操作的时间复杂度为O(1)，
    // 同理我们也可以得到插入操作的复杂度也为O(1)。
    //HashMap就是基于拉链法实现的散列表，它的默认负载因子为0.75。
    //
    // HashMap实现动态调整桶数的方式是基于公式loadFactor = maxSize / capacity，
    // 其中maxSize为支持存储的最大键值对数，而loadFactor和capacity（桶数）都会在初始化时由用户指定或是由系统赋予默认值。
    //
    // 当HashMap中的键值对的数目达到了maxSize时，就会增大散列表中的桶数。

    // 线性探测法处理碰撞：
    // 用大小为M的数组保存N个键值对，其中M > N，数组中的空位用于解决碰撞问题。（数组的大小为桶数的2倍，不支持动态调整数组大小。）
    // 线性探测法的主要思想是：当发生碰撞时（一个键被散列到一个已经有键值对的数组位置），我们会检查数组的下一个位置，这个过程被称作线性探测。
    // 线性探测可能会产生三种结果：
    //   命中：该位置的键与要查找的键相同；
    //   未命中：该位置为空；
    //   该位置的键和被查找的键不同。

    // 当我们查找某个键时，首先通过散列函数得到一个数组索引后，之后我们就开始检查相应位置的键是否与给定键相同，
    // 若不同则继续查找（若到数组末尾也没找到就折回数组开头），直到找到该键或遇到一个空位置。
    // 由线性探测的过程我们可以知道，若数组已满的时候我们再向其中插入新键，会陷入无限循环之中。
    //  if (num == capacity / 2) {
    //    resize(2 * capacity);     //扩容两倍
    //  }

}
