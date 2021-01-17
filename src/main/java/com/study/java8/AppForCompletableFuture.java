package com.study.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class AppForCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if(throwError){
                throw new IllegalArgumentException();
            }
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello ";
        }).handle((result , exceptionType) -> {
            // 첫번째 파라미터 - 정상적인 경우 반환되는 결과 값
            // 두번째 파라미터 - 예외 발생시 예외
            if(exceptionType != null){
                System.out.println("Exception Type : " + exceptionType);
                return "ERROR !!!";
            }
            return result;
        });

        System.out.println(hello.get());
        // 예외 발생 시 "ERROR !!!" 를 반환
        // 에외가 발생하지 않았을 시 "Hello" 반환
    }
}
