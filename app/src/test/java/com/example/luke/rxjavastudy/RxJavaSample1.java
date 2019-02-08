package com.example.luke.rxjavastudy;

import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

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

    @Test
    public void mergeTest() {
        Observable<String> a = Observable.create(s -> {
            new Thread(() ->{
                s.onNext("one");
                s.onNext("two");
                s.onNext("three");
                s.onComplete();
            }).start();
        });

        Observable<String> b = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("four");
                s.onNext("five");
                s.onNext("six");
                s.onComplete();
            }).start();
        });

        Observable<String> merge = Observable.merge(a,b);

        merge.subscribe(s -> System.out.println(s));
        merge.subscribe(s -> System.out.println(s));
    }

    @Test
    public void singleTest() {
        Single.create(s -> {
            s.onSuccess("test");
        }).subscribe(t -> System.out.println(t));
    }

    @Test
    public void completableTest() {
        Completable.create(s ->{
            s.onComplete();
        }).subscribe(() -> System.out.println("Completable"));
    }
}
