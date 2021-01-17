package com.study.java8;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class AppForSortTest {
    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime(); // 시작 시간
        Arrays.sort(numbers); // Thread를 하나만 쓴다. 퀵 정렬
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();  // 시작 시간
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));

        // 출력
        // serial sorting took 951400
        // parallel sorting took 716100
    }
}
