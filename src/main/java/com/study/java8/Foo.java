package com.study.java8;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo {
    public static void main(String[] args) {

    }

    private void run(){
        // final 없어도 참조가 가능하다.
        // final 없지만 어디에서도 이 변수를 수정하지 않는 것 (사실상 final - effective final)
        int baseNumber = 10;

        // 쉐도윙
        // Foo class -> run method -> 람다 , 익명 클래스 , 내부 클래스
        // 내부 클래스 , 익명 클래스 - 쉐도윙이 일어난다.
        // 람다 - 쉐도윙이 일어나지 않는다.

        // 람다 표현식 지역 변수 참조
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };
        printInt.accept(10);

        // 익명 클래스에서 지역 변수 참조
        Consumer<Integer> test2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 12;
                System.out.println(baseNumber);
            }
        };

        // 내부(로컬) 클래스에서 지역 변수 참조
        class LocalClass{
            void printBaseNumber(){
                int baseNumber = 13;
                System.out.println(baseNumber);
            }
        }
    }
}
