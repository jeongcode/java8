package com.study.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {

        // 인스턴스를 자세히 봐야 한다.

        // 스태틱 메서드 참조
        UnaryOperator<String> hi = Greeting::hi;
        String strHi = hi.apply("2021-01-10");
        System.out.println("스태틱 메서드 참조 : " + strHi);

        // 특정 객체의 인스턴스 메서드 참조
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        String strHello = hello.apply("2021-01-10");
        System.out.println("특정 객체의 인스턴스 메서드 참조 : " + strHello);

        String[] fruits = {"banana" , "grape" , "apple" };


        // JAVA8 부터 Comparator 도 함수형 인터페이스 이다. (compare 메서드)
        // 람다 표현식으로 변경 가능
        Arrays.sort(fruits, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        //람다 표현식
        Arrays.sort(fruits, (o1, o2) -> 0);

        // 람다 표현식이 가능하기 때문에 메서드 레퍼런스도 사용할 수 있다.
        // 임의 객체의 인스턴스 메서드 참조 - compareToIgnoreCase메서드를 사용한다.
        // compareToIgnoreCase - 자기 자신의 문자열과 파라미터로 받은 문자열을 비교 하여 int 값 반환
        Arrays.sort(fruits, String::compareToIgnoreCase);
        System.out.println("임의의 인스턴스들을 사용하여 정렬 : " + Arrays.toString(fruits));


        // 생성자 참조 (문자열을 받는)
        Function<String , Greeting> newFunction = Greeting::new;
        Greeting newGreeting1 = newFunction.apply("2021-01-10");
        System.out.println("생상자 참조(문자열을 받는) : " + newGreeting1.getName());

        // 기본 생성자 참조 (문자열을 받지 않는)
        Supplier<Greeting> newSupplier = Greeting::new;
        Greeting newGreeting2 = newSupplier.get();
        System.out.println("생성자 참조(문자열을 받지 않는) : " + newGreeting2.getName());
    }
}
