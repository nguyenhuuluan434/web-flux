package com.lsa.flux;

import io.reactivex.Observable;

public class CustomOperators {
    public static void main(String[] args) {
        String greeting = "Hello world!";

        Observable<String> observable = Observable.just(greeting);

        observable.subscribe(item -> System.out.println(item));
    }
}

