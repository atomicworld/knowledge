package org.atomic.java.java8.lambda;

import java.util.Arrays;

/**
 * Lambda表达式（也称为闭包）
 *  它允许我们将 "函数" 当成参数传递给某个方法，或者把代码本身当作数据处理;
 *  果Lambda表达式中的语句块只有一行，则可以不用使用return语句
 */
public class LambdaExpression {

    public static void main(String[] args) {

        Arrays.asList( "a", "b", "d" ).forEach(e -> {
            System.out.println(e);
            System.out.println(e+e);
        });

        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );//可以不用使用return语句

        //编译器满机智的，不会把 Integer 转成 Float
        Arrays.asList(1,2,3,4,5,6,7,8).forEach( (Integer a) -> System.out.println(a));  //强制转换成对象类型，而不能是 int型
        Arrays.asList(1f,2f,3f,4f,5f,6f,7f,8f).forEach( (Float c) -> System.out.println(c));  //强制转换成对象类型



    }

}
