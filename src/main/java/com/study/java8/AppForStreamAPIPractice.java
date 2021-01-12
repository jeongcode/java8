package com.study.java8;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppForStreamAPIPractice {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1 , "spring boot" , true));
        springClasses.add(new OnlineClass(2 , "spring data jpa" , true));
        springClasses.add(new OnlineClass(3 , "spring mvc" , false));
        springClasses.add(new OnlineClass(4 , "spring core" , false));
        springClasses.add(new OnlineClass(5 , "rest api development" , false));

        System.out.println(" spring으로 시작하는 수업 ");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));
        // 필터에 걸린 객체를 리스트로 받기
        List<OnlineClass> exam1List = springClasses.stream()
                 .filter(oc -> oc.getTitle().startsWith("spring"))
                 .collect(Collectors.toList());

        exam1List.forEach(System.out::println);

        System.out.println(" close되지 않은 수업 ");
        springClasses.stream()
                .filter(oc -> !oc.isClosed())
                .forEach(oc -> System.out.println(oc.getId()));

        // 스태틱 메서드와 메서드 레퍼런스 활용
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));

        // 필터에 걸린 객체를 리스트로 받기
        List<OnlineClass> exam2List = springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .collect(Collectors.toList());

        exam2List.forEach(System.out::println);


        System.out.println(" 수업 이름만 모아서 스트림 만들기 ");
        // 중개 오퍼레이터인 map은 객체를 map으로 받아들여 나갈때는 다른 타입으로 변경할 수 있다.
        springClasses.stream()
                        .map(OnlineClass::getTitle)
                        .forEach(System.out::println);


        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6 , "The Java , Test" , true));
        javaClasses.add(new OnlineClass(7 , "The Java , Code manipulation" , true));
        javaClasses.add(new OnlineClass(8 , "The Java , 8 to 11" , false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println(" 두 수업 목록에 들어 있는 모든 수업 아이디 출력 ");
        // 메서드 레퍼런스
        keesunEvents.forEach(oc -> {
            oc.forEach(System.out::println);
        });
        // flatMap
        // forEach에는 리스트안의 객체 타입으로 들어간다.
        keesunEvents.stream().flatMap(Collection::stream)
                .forEach(System.out::println);

        System.out.println(" 1부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만 ");
        Stream.iterate(1 , i  -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println(" 자바 수업 중에 Test가 들어 있는 수업이 있는지 확인 ");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println(" 스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기 ");
        List<String> titleList = springClasses.stream()
                                        .map(OnlineClass::getTitle)
                                        .filter(t -> t.contains("spring"))
                                        .collect(Collectors.toList());
        titleList.forEach(System.out::println);

        System.out.println(" 스프링 수업 중에 제목에 spring이 들어간 객체를 모아서 List로 만들기 ");
        List<OnlineClass> objList = springClasses.stream()
                                            .filter(oc -> oc.getTitle().contains("spring"))
                                            .collect(Collectors.toList());
        objList.forEach(System.out::println);
    }
}
