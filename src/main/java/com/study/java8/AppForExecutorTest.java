package com.study.java8;

import java.util.concurrent.*;

public class AppForExecutorTest {
    public static void main(String[] args) {
        // 2개의 Thread를 선언
        ScheduledExecutorService scheduledExecutorService
                = Executors.newSingleThreadScheduledExecutor();

        // 시작 1초 지연
        scheduledExecutorService
                .schedule(getRunnable("Thread1") , 1 , TimeUnit.SECONDS);

        // 시작 1초 지연 후 2초 간격으로 반복 실행
        scheduledExecutorService
                .scheduleAtFixedRate(getRunnable("Thread2") , 1 , 2, TimeUnit.SECONDS);

    }

    private static Runnable getRunnable(String message) {
        return () -> {
            System.out.println(message + " : " + Thread.currentThread().getName());
        };
    }
}
