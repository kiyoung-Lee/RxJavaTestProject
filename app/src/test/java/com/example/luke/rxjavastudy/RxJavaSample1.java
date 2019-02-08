package com.example.luke.rxjavastudy;

import org.junit.Test;

import io.reactivex.Observable;

public class RxJavaSample1 {

    @Test
    public void ObservableTest1() {
        Observable.create(s -> {
            s.onNext("Hello World");
            s.onComplete();
        }).subscribe(hello -> System.out.println(hello));
    }

    @Test
    public void ObservableTest2() {
        Observable<Integer> o = Observable.create(s ->{
            s.onNext(1);
            s.onNext(2);
            s.onNext(3);
            s.onComplete();
            s.onNext(4);
            s.onNext(5);
        });

        o.map(i -> "Number" + i)
         .subscribe(s -> System.out.println(s));
    }
}
