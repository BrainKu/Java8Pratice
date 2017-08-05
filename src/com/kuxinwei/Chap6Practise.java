package com.kuxinwei;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brainku on 17/8/5.
 */
public class Chap6Practise {

    public static void main(String[] args) {
        System.out.println(allToUpperCase(Arrays.asList("abc", "edf", "ghi")));
        System.out.println(allFirstToUpperCase(Arrays.asList("abc", "edf", "ghi")));
    }

    public static List<String> allToUpperCase(List<String> words){
        return words.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static List<String> allFirstToUpperCase(List<String> words) {
        return words.stream().map(Chap6Practise::firstToUpperCase).collect(Collectors.toList());
    }

    private static String firstToUpperCase(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
