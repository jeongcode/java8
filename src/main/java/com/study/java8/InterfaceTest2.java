package com.study.java8;

public interface InterfaceTest2 extends InterfaceTest{

    // 추상 메서드를 재정의 하지 않으면 InterfaceTest에서 제공하는
    // printNameUpperCase 구현체가 제공된다.
    // 만약, 재정의 하지 않고 InterfaceTest2를 구현하게 되어도
    // 그 구현체는 InterfaceTest의 printNameUpperCase의 메서드를 사용할 수 있다.
    default void printNameUpperCase(){
        System.out.println("print name Upper case2 : " + getName().toUpperCase());
    }

}
