package com.study.java8;

import javax.lang.model.SourceVersion;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppForOptionalTest {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1 , "spring boot" , true));
        springClasses.add(new OnlineClass(5 , "rest api development" , false));

        // Optional로 리턴되는 스트림의 종료 오퍼레이션이 존재한다.
        Optional<OnlineClass> spring = springClasses.stream()
                    .filter(oc -> oc.getTitle().startsWith("spring"))
                    .findFirst();

        // 존재하는지 ?
        boolean isPresent = spring.isPresent();
        System.out.println(isPresent);    // true

        // 비었는지 ?
        boolean isEmpty = spring.isEmpty();    // JAVA11부터
        System.out.println(isEmpty);   // false

        // 값 가져오기 (값이 있을 때)
        OnlineClass get = spring.get();
        System.out.println(get);

        // 값이 없을 때 get을 바로 하게 되면 예외 발생
        OnlineClass onlineClass2 = spring.get();
        System.out.println(onlineClass2);
        // java.util.NoSuchElementException
        // ifPresent를 사용하여 값이 있는지 없는지 체크하여야 한다.
        spring.ifPresent(oc -> System.out.println(oc.getTitle()));

        // orElse는 Optional 값이 있든 없든 orElse(...) 안의 ...은 무조건 실행된다.
        OnlineClass orElse1 = spring.orElse(createNewClass());
        OnlineClass orElse2 = spring.orElse(new OnlineClass(11 , "NewClass2" , false));

        // orElseGet은 Optional 값이 있으면 orElseGet(...) 안의 ...은 실행되지 않는다.
        // 람다 표현식
        OnlineClass orElseGet1 = spring.orElseGet(() -> createNewClass());
        // 메서드 레퍼런스
        OnlineClass orElseGet2 = spring.orElseGet(AppForOptionalTest::createNewClass);

        // orElseThrow
        // 값이 존재 하지 않으면 java.util.NoSuchElementException 예외를 발생한다.
        OnlineClass orElseThrow1 = spring.orElseThrow();
        // 메서드 레퍼런스 (예외를 지정할 수도 있다.)
        OnlineClass orElseThrow2 = spring.orElseThrow(IllegalArgumentException::new);

        // filter
        Optional<OnlineClass> filter = spring.filter(oc -> !oc.isClosed());

        // map
        // 메소드 레퍼런스
        Optional<String> strMap = spring.map(OnlineClass::getTitle);

        // flatMap
        // OnlineClass Progress는 Optional<Progress>를 반환한다.
        // 그러면 Optional<Optional<Progress>>가 된다.
        // 이걸 유용하게 꺼낼 수 있게 해주는 메소드 flatMap이다.
        Optional<Progress> flatMap = spring.flatMap(OnlineClass::getProgress);

        // flatMap을 사용하지 않으면 이렇게 2번 체크해야 한다.
        Optional<Optional<Progress>> progress1 = spring.map(OnlineClass::getProgress);
        Optional<Progress> progress2 = progress1.orElse(Optional.empty());
    }
    private static OnlineClass createNewClass(){
        return new OnlineClass(10 , "New Class" , false);
    }
}
