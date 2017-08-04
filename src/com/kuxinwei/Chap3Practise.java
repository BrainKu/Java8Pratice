package com.kuxinwei;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Chap3
 * Created by n3072 on 2017/8/4.
 */
public class Chap3Practise {

    public static void main(String[] args) {
        System.out.println(addUp(createIntList(10)));
        System.out.println(getName(Artist.createList(10)));
        System.out.println(getMemberCount(Artist.createList(5)));
        System.out.println(charInString("abcdeABCDE"));
        System.out.println(lessLittleChar(Arrays.asList("abcdefg", "ABCDEF", "AbCDde")));
        System.out.println(lessLittleChar(Collections.emptyList()));
        List<Artist> artists = Artist.createList(10);
        System.out.println(collectNameByMap(artists));
        System.out.println(collectNameByReduce(artists));
        System.out.println(filterArtistByFilter(artists));
        System.out.println(filterArtistByReduce(artists));
    }

    private static List<Integer> createIntList(int size) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            integers.add(i+1);
        }
        return integers;
    }

    public static int addUp(List<Integer> numbers) {
        return numbers.stream().reduce(0, (x, y) -> (x + y));
    }

    public static List<String> getName(List<Artist> artists) {
        return artists.stream().map(artist -> artist.country).collect(toList());
    }

    // 2 迭代
    public static int getMemberCount(List<Artist> artists) {
        return (int) artists.stream().flatMap(Artist::getMembers).count();
    }

    // 3 a 求值， b 惰性求值

    // 6 求出字符串中小写字母个数
    public static long charInString(String input) {
        return input.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
    }

    // 7 找出字符串列表小写字母最多的字符串
    public static Optional<String> lessLittleChar(List<String> inputs) {
        return Optional.of(inputs).filter(it -> it != null && !it.isEmpty()).flatMap(strings -> {
            long min = Integer.MAX_VALUE;
            String minString = "";
            for (String str : strings) {
                long count = charInString(str);
                if (count < min) {
                    min = count;
                    minString = str;
                }
            }
            return Optional.of(minString);
        });
    }

    // 10 进阶练习
    // 1 只用 reduce 和 Lambda 表达式实现 map，返回是 stream 或者 list

    public static List<String> collectNameByMap(List<Artist> artists) {
        return artists.stream().map(artist -> artist.name).collect(toList());
    }

    public static List<String> collectNameByReduce(List<Artist> artistList) {
        return artistList.stream().reduce(new ArrayList<>(), (strings, artist) -> {
            strings.add(artist.name);
            return strings;
        }, (string, string2) -> {
            System.out.println("reduce equal:" + (string == string2)); // 根本没有打出这句 log
            return string;
        });
    }

    public static List<Artist> filterArtistByFilter(List<Artist> artists) {
        return artists.stream().filter(artist -> artist.name.contains("2")).collect(toList());
    }

    public static List<Artist> filterArtistByReduce(List<Artist> artistList) {
        return artistList.stream().reduce(new ArrayList<>(), (artists, artist) -> {
            if (artist.name.contains("2")) {
                artists.add(artist);
            }
            return artists;
        }, ((artists, artists2) -> artists));
    }

    public static class Artist {
        public static int count = 0;
        public String name;
        public String country;
        public List<Artist> members;

        public Artist() {
            count++;
            name = hashCode() + "";
            country = this.getClass().getSimpleName() + name;
            members = createList(5, false);
        }

        public Artist(boolean needMember) {
            count++;
            name = hashCode() + "";
            country = this.getClass().getSimpleName() + name;
            if (needMember) {
                members = createList(5, false);
            }
        }

        public Stream<Artist> getMembers() {
            return members.stream();
        }

        @Override
        public String toString() {
            return "artist " + hashCode();
        }

        public static List<Artist> createList(int size, boolean member) {
            List<Artist> artists = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                artists.add(new Artist(member));
            }
            return artists;
        }

        public static List<Artist> createList(int size) {
            List<Artist> artists = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                artists.add(new Artist());
            }
            return artists;
        }
    }
}
