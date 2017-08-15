package com.kuxinwei.rx;

import com.sun.tools.javac.util.Pair;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Chap3 {
    public static void main(String[] args) throws InterruptedException {
        speak(" I m the king of the world", 500).subscribe(System.out::println);
        Thread.sleep(10000);
    }

    // 输入一串单词，依次显示每个单词，前后单词的间隔是前面单词的长度乘以单位时间
    // 这里的重点是 scan，输出的是出现当前单词需要的时间（从头开始），
    // 而不再需要用 concat 这种方式，concat 也只适用于长度有限的 Observable 吧
    public static Observable<String> speak(String words, long millPerChat) {
        String[] chars = words.split(" ");
        Observable<String> contents = Observable.from(chars);
        Observable<Long> wordsTime = contents.map(s -> s.length() * millPerChat).scan((total, current) -> total + current);
        return contents.zipWith(wordsTime.startWith(0L), Pair::of)
                .flatMap(stringLongPair ->
                        Observable.just(stringLongPair.fst).delay(stringLongPair.snd, TimeUnit.MILLISECONDS));
    }
}
