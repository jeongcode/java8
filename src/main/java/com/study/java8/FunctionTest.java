package com.study.java8;

import java.util.function.Function;

public class FunctionTest implements Function<Integer , Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 100;
    }
}
