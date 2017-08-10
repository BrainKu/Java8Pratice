package com.kuxinwei.java8;

import java.util.IntSummaryStatistics;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by brainku on 17/8/5.
 */
public class Chap4Practise {
    @FunctionalInterface // 纯声明，作为函数接口需要满足只有单个抽象方法的要求
    private interface IntPredicate {
        boolean test(int value);
    }

    private void overloadedMethod(Predicate<Integer> predicate) {
        System.out.println("Predicate");
    }

    private void overloadedMethod(IntPredicate intPredicate) {
        System.out.println("IntPredicate");
    }

    public static void main(String[] args) {
        Chap4Practise chap4Practise = new Chap4Practise();
//        chap4Practise.overloadedMethod((x) -> true); // 编译不过，lambda 表达式有歧义
        chap4Practise.overloadedMethod((IntPredicate) value -> false); // 这里需要显式声明类型，否则 lambda 表达式两个都可以匹配
        chap4Practise.testIntStream();
    }

    public void testIntStream() {
        IntStream intStream = Stream.of(12,4,4,2,1,4,4,5).mapToInt(Integer::intValue);
        IntSummaryStatistics statistics = intStream.summaryStatistics();
        System.out.println(statistics.toString());
    }

    public interface A {
        default void hello(String str){}
    }

    public interface B {
        default void hello(String str) {}

    }

    public static class C implements A, B {

        @Override
        public void hello(String str) {
            A.super.hello(str);
            B.super.hello(str);
        }
    }
}
