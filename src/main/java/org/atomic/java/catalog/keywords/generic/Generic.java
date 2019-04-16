package org.atomic.java.catalog.keywords.generic;

/**
 * 泛型：
 *  Liskov替换原则：所有引用基类（父类）的地方必须能透明地使用其子类的对象。
 *      f(⋅)f(⋅) 是逆变（contravariant）的，当A≤BA≤B时有f(B)≤f(A)f(B)≤f(A)成立；
 *      f(⋅)f(⋅)是协变（covariant）的，当A≤BA≤B时有f(A)≤f(B)成立f(A)≤f(B)成立；
 *      f(⋅)f(⋅)是不变（invariant）的，当A≤BA≤B时上述两个式子均不成立，即f(A)f(A)与f(B)f(B)相互之间没有继承关系。
 *
 *  <? extends>实现了泛型的协变
 *  <? super>实现了泛型的逆变
 *
 *   producer-extends, consumer-super.
 *    1.要从泛型类取数据时，用extends；
 *    2.要往泛型类写数据时，用super；
 *    3.既要取又要写，就不用通配符（即extends与super都不用）。
 */
public class Generic {



}
