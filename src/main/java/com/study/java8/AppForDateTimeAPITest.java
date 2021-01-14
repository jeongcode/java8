package com.study.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppForDateTimeAPITest {
    public static void main(String[] args){
        Instant instant = Instant.now();
        System.out.println(instant);
        // 현재 시간은 2021-01-14 22:08분
        // 출력 - 2021-01-14T13:08:28.794934700Z
        // 기준 시간이 UTC , GMT 이기 때문에 시간이 다르다.

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        // 출력 - Asia/Seoul
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);
        // 출력 - 2021-01-14T22:11:49.055336800+09:00[Asia/Seoul]

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        // 현재 시간은 20210-01-14 22:16
        // 출력 - 2021-01-14T22:16:06.431744200

        LocalDateTime birth =
                LocalDateTime.of(1993 , Month.APRIL , 30 , 0 , 0 , 0);
        System.out.println(birth);
        // 출력 - 1993-04-30T00:00

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);
        // 출력 - 2021-01-14T22:18:52.191717500+09:00[Asia/Seoul]

        Instant beforeInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = beforeInstant.atZone(ZoneId.of("Asia/Seoul"));
        Instant afterInstant = zonedDateTime1.toInstant();
        // ZonedDateTime 과 Instant는 서로 변환이 가능하다.

        LocalDate today = LocalDate.now(); // 2021-01-14
        LocalDate dateOfBirth = LocalDate.of(1993 , Month.APRIL , 30);
        Period period = Period.between(dateOfBirth , today);
        System.out.println(period.getYears());  // 27
        System.out.println(period.getMonths()); // 8
        System.out.println(period.getDays());   // 15

        Period period1 = dateOfBirth.until(today);
        System.out.println(period1.get(ChronoUnit.DAYS));   // 15


        Instant now2 = Instant.now();
        Instant plus = now2.plus(10, ChronoUnit.DAYS);
        Duration diff = Duration.between(now2 , plus);
        System.out.println(diff);   // PT240H

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(now.format(formatter)); // 2021-01-14

        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse("2021/01/14", parseFormatter);
        System.out.println(localDate);  // 2021-01-14

        Date date = new Date();
        // Instant <- Date
        Instant instant1 = date.toInstant();
        // Date <- Instant
        Date newDate = Date.from(instant1);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        // Instant <- GregorianCalendar
        Instant toInstant = gregorianCalendar.toInstant();

        // ZonedDateTime <- GregorianCalendar
        ZonedDateTime toZonedDateTime = gregorianCalendar.toInstant()
                .atZone(ZoneId.systemDefault());

        // LocalDateTime <- GregorianCalendar
        LocalDateTime toLocalDateTime = gregorianCalendar.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();

        // GregorianCalendar <- toZonedDateTime
        GregorianCalendar from = GregorianCalendar.from(toZonedDateTime);
    }
}
