package com.example.kiyounglee.rxtest;

import org.junit.Test;
import org.junit.internal.matchers.ThrowableCauseMatcher;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;


public class ExampleUnitTest
{
    @Test
    public void Test() throws Exception {
        String value = "abcdefg";

        int length = value.length();
        char temp;
        for (int i = 0; i< length; i ++){
            temp = value.charAt(i);
            System.out.println(temp);
        }

        String value1 = "abcdefg";
        List<String> values = Arrays.asList("1", "2", "3", "4", "5");

        Observable.fromIterable(values)
                .map(Integer::parseInt)
                .flatMap(input -> Observable.range(input * 10 -9, 10))
                .subscribe(s -> System.out.println(s.getClass().getSimpleName() + " :" + s));


        //doOnNext
        //map
        //subscribe
        //flatmap

        // 1~10 -> 1~100 , toString -> String Buffer

        int[] numArray = {};
        StringBuffer buffer = new StringBuffer();
        Observable.fromArray(1,2,3,4,5,6,7,8,9,10)
                .flatMap(input -> Observable.range(input * 10 - 9, 10))
                .subscribe(s -> buffer.append(s),
                        Throwable::printStackTrace,
                        () -> System.out.println(buffer.toString()));

    }
}