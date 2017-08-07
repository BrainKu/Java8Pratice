package com.kuxinwei;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Track track = new Track();
        Set<String> contents = track.getAlbumList().stream().filter((album) -> album.isBand).map((album -> album.content)).collect(Collectors.toSet());
        System.out.println(contents);
    }

    public static class Track {
        public List<Album> getAlbumList() {
            return Album.createContents();
        }
    }


    public void testFunctionMethod() {
        Thread thread = new Thread();
    }

    public void runAction(Callable runnable) {
    }

    public boolean isPrime(int value) {
        return IntStream.range(2, value).allMatch(x -> x%value != 0);
    }

    ThreadLocal<String> mThreadLocal = ThreadLocal.withInitial(() -> "ABCDEFG");

    public static class Album {
        static Random random = new Random();

        public String content;
        public boolean isBand;

        public static Album create() {
            Album a = new Album();
            a.content = a.toString();
            a.isBand = random.nextBoolean();
            return a;
        }

        public static List<Album> createContents() {
            List<Album> alba = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                alba.add(create());
            }
            return alba;
        }
    }
}
