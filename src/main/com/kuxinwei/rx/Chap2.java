package com.kuxinwei.rx;

import rx.Observable;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by brainku on 17/8/7.
 */
public class Chap2 {

    public static void main(String[] args) {
        System.out.println("hello world");
//        testTimer();
        waitByWordLength();
    }

    static void testTimer() {
        System.out.println("log start");
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe((aLong) -> {
                    System.out.println("call action");
                    System.out.println(Thread.currentThread());
                });
        System.out.println("log end");
        try {
            Thread.sleep(2 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static <T> Observable<T> delay(T x) {
        return Observable.create(
                subscriber -> {
                    Runnable r = () -> {};
                    Thread thread = new Thread();
                    thread.start();
                    subscriber.add(Subscriptions.create(thread::interrupt));
                }
        );
    }

    static void waitByWordLength() {
//        Observable.just("ab", "asdfasdf", "a", "asdfa")
//                .delay(word -> Observable.timer(word.length(), TimeUnit.SECONDS))
//                .subscribe(System.out::println);
        // use flapMap + timer implement delay
        Observable.just("ab", "asdfasdf", "a", "asdfa").flatMap((Func1<String, Observable<?>>) word -> Observable.timer(word.length(), TimeUnit.SECONDS).map(x -> word)
        ).subscribe(System.out::println);

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class User {
        Observable<User> loadProfile() {
            return null;
        }
    }

    List<User> sLargeUsers = new ArrayList<>();

    void testLoadProfile() {
        Observable.from(sLargeUsers).flatMap((Func1<User, Observable<?>>) User::loadProfile, 10);
    }
}
