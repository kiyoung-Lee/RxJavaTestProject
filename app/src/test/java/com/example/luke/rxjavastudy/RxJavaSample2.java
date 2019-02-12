package com.example.luke.rxjavastudy;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

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

        stream.subscribe(
                System.out::println,
                Throwable::printStackTrace
        );
    }

    @Test
    public void SubscriptionTest() {
        Observable<String> sampleStream = Observable.create(s -> {
            s.onNext("test");
            s.onComplete();
        });

        Subscription subscription = (Subscription) sampleStream.subscribe();
        subscription.cancel();

        Disposable disposable = sampleStream.subscribe();
        disposable.dispose();
    }

    @Test
    public void SubscriberTest() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Print!!" + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };


        Observable<String> subscriberStream = Observable.create(s -> {
            s.onNext("Test");
            s.onComplete();
        });

        // RxJava2 에서는 안됨..
//        subscriberStream.subscribe(subscriber);
    }

    @Test
    public void cacheTest() {
        Observable<String> cacheStream = Observable.<String>create(s ->{
            System.out.println("cacheStart");
            s.onNext("test1");
            s.onNext("test2");
            s.onComplete();
        }).cache();

        System.out.println("Start");
        cacheStream.subscribe(s -> System.out.println(s));
        cacheStream.subscribe(s -> System.out.println(s));
        System.out.println("End");
    }


    @Test
    public void subjectTest() {
        PublishSubject<String> subject = PublishSubject.create();

        subject.onNext("test1");

        Observable subjectObservable = subject;

        subjectObservable.subscribe(s -> System.out.println(s));

        subject.onNext("test2");
        subject.onNext("test3");
    }

    @Test
    public void asyncSubjectTest() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.onNext("test1");
        asyncSubject.onNext("test2");
        asyncSubject.onNext("test3");

        Observable<String> asyncSubjectObservable = asyncSubject;

        asyncSubjectObservable.subscribe(s -> System.out.println(s));

        asyncSubject.onComplete();
        asyncSubject.onNext("test4");
    }

    @Test
    public void behaviorSubjectTest() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.onNext("test1");
        behaviorSubject.onNext("test2");
        behaviorSubject.onNext("test3");

        Observable<String> behaviorSubjectObservalbe = behaviorSubject;

        behaviorSubjectObservalbe.subscribe(s -> System.out.println(s));

        behaviorSubject.onNext("test4");
        behaviorSubject.onNext("test5");
    }

    @Test
    public void replaySubjectTest() {
        ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject.onNext("test1");
        replaySubject.onNext("test2");

        Observable<String> replaySubjectObservable = replaySubject;

    }
}
