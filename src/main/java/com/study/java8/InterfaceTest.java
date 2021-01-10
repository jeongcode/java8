package com.study.java8;

public interface InterfaceTest {
    void printName();
    String getName();
//    void printNameUpperCase();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase(){
        System.out.println("print name Upper case : " + getName().toUpperCase());
    }

//    void printNameUpperCase(); 를 추상메서드로 추가하게 되면
//    이 인터페이스를 구현하고있는 구현체를 다 수정하여야 하는 번거로움이 생긴다.

//    인터페이스에 메서드를 default를 선언하고 로직(바디)을 작성해 놓으면
//    이 인터페이스를 구현하는 구현체는 default 메서드를 쓸 수 있다.

    static void printAnything(){
        System.out.println("Interface Test");
    }
}
