package com.study.java8;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class AppForCallableAndFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> test1 = () ->{
            Thread.sleep(1000L);
            return "Test1";
        };
        Callable<String> test2 = () ->{
            Thread.sleep(2000L);
            return "Test2";
        };
        Callable<String> test3 = () ->{
            Thread.sleep(3000L);
            return "Test3";
        };
        Callable<String> test4 = () ->{
            Thread.sleep(4000L);
            return "Test4";
        };
        long start = System.currentTimeMillis();
        List<Future<String>> list =
                executorService.invokeAll(Arrays.asList(test1, test2, test3, test4));
        long end = System.currentTimeMillis();
        for(Future<String> future : list){
            System.out.println(future.get());
        }
        System.out.println("경과 : " + (end - start) / 1000);
//        출력
//        Test1
//        Test2
//        Test3
//        Test4
//        경과 : 10

//        만약 스레드가 4개라면 4초가 걸린다.
        executorService.shutdown();
    }
}
