package com.kuxinwei.rx;

import rx.Observable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Chap6 {

    public static void main(String[] args) throws InterruptedException {
//        simpleBuffer();
//        bufferSkipMoreCount();
        bufferByTime();
//        movingAverage();
    }

    private static void bufferByTime() throws InterruptedException {
        Observable<String> name = Observable.just("A", "B", "C" , "D", "E",
                "F", "G", "H", "I", "J");
        Observable<Long> nameDelays = Observable.just(0.1, 0.6, 0.9, 1.1, 3.3,
                3.4, 3.5, 3.6, 4.4, 4.8).map( second -> (long)(second * 1000));
        Observable<String> names = name.zipWith(nameDelays, (n, d) -> Observable.just(n).delay(d, TimeUnit.MILLISECONDS))
                .flatMap(ob -> ob);
        names.buffer(1, TimeUnit.SECONDS).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(8);
    }

    /**
     * skip step larger than buffer size. It means get first element and skip two step (first and second), and so on
     */
    private static void bufferSkipMoreCount() {
        Observable.range(1, 7)
                .buffer(1, 2)
                .flatMapIterable(list -> list)
                .subscribe(System.out::println);
    }

    private static void simpleBuffer() {
        Observable.range(1, 3)
                .buffer(3, 1)
                .subscribe(System.out::println);
    }

    private static void movingAverage() {
        Random random = new Random();
        Observable.defer(() -> Observable.just(random.nextGaussian()))
                .repeat(1000)
                .buffer(100,1)
                .map(Chap6::averageOfList)
                .subscribe(System.out::println);
    }

    private static double averageOfList(List<Double> list) {
        return list.stream().collect(Collectors.averagingDouble(x -> x));
    }
}
