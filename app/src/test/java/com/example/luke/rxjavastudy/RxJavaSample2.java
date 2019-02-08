package com.example.luke.rxjavastudy;

import org.junit.Test;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaSample2 {

    @Test
    public void ObserverDetailTest() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("finish");
            }
        };


        Observable<String> stream = Observable.create(s ->{
            s.onNext("Observer!!!");
            s.onComplete();
        });

        stream.subscribe(observer);
    }

    @Test
    public void argumentTest() {
        Observable<String> stream = Observable.create(s ->{
            s.onNext("Observer!!!");
            s.onComplete();
        });

        stream.subscribe(
                (String str) -> System.out.println(str),
                (Throwable t) -> t.printStackTrace(),
                () -> System.out.println("OnComplete!!")
        );
    }
}
