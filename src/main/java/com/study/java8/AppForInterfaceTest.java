package com.study.java8;

import java.awt.*;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppForInterfaceTest implements InterfaceTest{
    String name;

    public AppForInterfaceTest(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("print name : " + this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }


    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("test1");
        strList.add("test2");
        strList.add("test3");
        strList.add("test4");
        strList.add("test5");

        // 쪼갤수 있는 기능을 가진 iterator
        Spliterator<String> spliterator = strList.spliterator();
        // List를 Spliterator로 변환한 것을 절반으로 나눈다.
        Spliterator<String> spliterator1 = spliterator.trySplit();
        Spliterator<String> spliterator2 = spliterator.trySplit();

        // hasNext가 아닌 tryAdvance
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("=============================");
        while(spliterator1.tryAdvance(System.out::println));
        System.out.println("=============================");
        while(spliterator2.tryAdvance(System.out::println));

//        출력 (spliterator가 2개일 때)
//        test3
//        test4
//        test5
//        =============================
//        test1
//        test2

//        출력 (spliterator가 3개일 때)
//        test4
//        test5
//        =============================
//        test1
//        test2
//        =============================
//        test3

        System.out.println("********************************");
        ArrayList<String> strList2 = new ArrayList<>();
        strList2.add("test1");
        strList2.add("test2");
        strList2.add("test3");
        strList2.add("test3");
        strList2.add("test4");

        long cnt = strList2.stream()
                            .filter(s -> s.endsWith("3"))
                            .count();
        System.out.println(cnt);

        // 출력
        // 2

        strList2.removeIf(s -> s.endsWith("4"));
        strList2.forEach(System.out::println);

//        출력
//        test1
//        test2
//        test3
//        test3

        System.out.println("=============================");
        ArrayList<String> strList3 = new ArrayList<>();
        strList3.add("test1");
        strList3.add("tEst2");
        strList3.add("teSt3");
        strList3.add("TEST3");
        strList3.add("TesT4");

        // compareToIgnoreCase - 대소문자 무시
        strList3.sort(String::compareToIgnoreCase);
        strList3.forEach(System.out::println);

        System.out.println("=============================");

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        strList3.sort(compareToIgnoreCase.reversed());
        strList3.forEach(System.out::println);

        System.out.println("=============================");

        List<String> names = new ArrayList<>();
        names.add("jeong");
        names.add("park");
        names.add("baek");
        names.add("kim");
        names.add("lee");

        List<String> collectTest = names.stream().map((s) -> {
            System.out.println("stream 처리 : " + s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        // 병렬 처리
        // (spliterator의 trySplit을 사용하여 쪼개서 처리한다.)
        List<String> collectTest2 = names.parallelStream().map((s) -> {
            System.out.println("parallelStream 처리 : " + s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

//        출력
//        stream 처리 : jeong main
//        stream 처리 : park main
//        stream 처리 : baek main
//        stream 처리 : kim main
//        stream 처리 : lee main
//        parallelStream 처리 : baek main
//        parallelStream 처리 : lee ForkJoinPool.commonPool-worker-3
//        parallelStream 처리 : park ForkJoinPool.commonPool-worker-5
//        parallelStream 처리 : kim ForkJoinPool.commonPool-worker-3
//        parallelStream 처리 : jeong ForkJoinPool.commonPool-worker-5



        // 출력 되지 않는다.
        // 종료형 오퍼레이터가 실행 되기 전에는 중개형 오퍼레이터는 실행 하지 않는다.
        // (단지 정의만 한것이다.)
        Stream<Object> collect3 = names.stream().map((s) ->{
            System.out.println("종료형 오퍼래이터가 붙지 않고 , 인스턴스가 사용되지 않을 때: " + s);
            return s.toUpperCase();
        });

        Stream<Object> collect4 = names.stream().map((s) ->{
            System.out.println("종료형 오퍼래이터가 붙지 않고 , 인스턴스가 사용될 때 : " + s);
            return s.toUpperCase();
        });
        collect4.forEach(System.out::println);

//        출력
//        종료형 오퍼래이터가 붙지 않고 , 인스턴스가 사용될 때 : jeong
//        JEONG
//        종료형 오퍼래이터가 붙지 않고 , 인스턴스가 사용될 때 : park
//        PARK
//        종료형 오퍼래이터가 붙지 않고 , 인스턴스가 사용될 때 : baek
//        BAEK
//        종료형 오퍼래이터가 붙지 않고 , 인스턴스가 사용될 때 : kim
//        KIM
//        종료형 오퍼래이터가 붙지 않고 , 인스턴스가 사용될 때 : lee
//        LEE
        System.out.println("-------------------------------------");
        // 종료형 오퍼레이터 (collect)가 붙었을 때
        List<String> collect2 = names.stream().map((s) ->{
                                System.out.println("종료형 오퍼래이터가 붙었을 때 : " + s);
                                return s.toUpperCase();
                            }).collect(Collectors.toList());

//        출력
//        종료형 오퍼래이터가 붙었을 때 : jeong
//        종료형 오퍼래이터가 붙었을 때 : park
//        종료형 오퍼래이터가 붙었을 때 : baek
//        종료형 오퍼래이터가 붙었을 때 : kim
//        종료형 오퍼래이터가 붙었을 때 : lee

        // 대문자로 변경된 데이터는 strStream에 담겨 있다.
//        strStream.forEach(System.out::println);

        System.out.println("=============================");
        // names 의 데이터가 변경 되지 않은걸 볼 수 있다.
        names.forEach(System.out::println);

//        출력
//        JEONG
//        PARK
//        BAEK
//        KIM
//        LEE
//        =============================
//        jeong
//        park
//        baek
//        kim
//        lee

    }
}
