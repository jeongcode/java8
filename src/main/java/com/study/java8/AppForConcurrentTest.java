package com.study.java8;


public class AppForConcurrentTest {
    public static void main(String[] args) throws InterruptedException {
        // InterruptedException 테스트
        Thread testThread = new Thread(() -> {
            try {
                Thread.sleep(3000L);
                System.out.println("Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        testThread.start();

        System.out.println("Hello : " + Thread.currentThread().getName());
        // main thread 가 testThread를 기다린다.
        testThread.join();

        System.out.println(Thread.currentThread().getName() + " is finished ");

//        출력
//        Hello : main
//        3초 후
//        Thread : Thread-0
//        main is finished

//        원래라면 man is finished는 main thread에 의해 "Thread : Thread-0" 이전에 나와야한다.
//        하지만 join 메서드로 인해 main thread는 testThread를 기다리게 된다.
    }
}
