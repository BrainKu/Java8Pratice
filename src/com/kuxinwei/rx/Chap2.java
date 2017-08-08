package com.kuxinwei.rx;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by brainku on 17/8/7.
 */
public class Chap2 {

    public static void main(String[] args) {
        System.out.println("hello world");
        testTimer();
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
}
