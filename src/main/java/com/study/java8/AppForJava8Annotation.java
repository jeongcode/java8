package com.study.java8;

import java.util.Arrays;


@Chicken("양념")
@Chicken("마늘")
@Chicken("간장")
@Chicken("후라이드")
public class AppForJava8Annotation {

    public static void main(String[] args) {
        Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);

        Arrays.stream(chickens).forEach( c ->{
            System.out.println(c.value());
        });

        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }
}
