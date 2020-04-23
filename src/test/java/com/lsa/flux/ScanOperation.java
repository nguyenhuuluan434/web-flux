package com.lsa.flux;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ScanOperation {
    //The scan operator applies a function to each item emitted by an Observable sequentially and emits each successive value.
    public static void main(String[] args) throws InterruptedException {
        Observable<String> alice = speak(
                "To be, or not to be: that is the question", 110);
        Observable<String> bob = speak(
                "Though this be madness, yet there is method in't", 90);

        Observable<String> jane = speak("There are more things in Heaven and Earth, " +
                "Horatio, than are dreamt of in your philosophy", 100);
//        Observable.concat(
//                alice.map(w -> "Alice: " + w),
//                bob.map(w -> "Bob: " + w),
//                jane.map(w -> "jane " + w)
//        ).subscribe(System.out::println);

//        Observable.merge(
//                alice.map(w -> "Alice: " + w),
//                bob.map(w -> "Bob: " + w),
//                jane.map(w -> "jane " + w)
//        ).subscribe(System.out::println);

        Observable<Observable<String>> quotes = Observable.just(alice.map(w -> "Alice: \t" + w),
                bob.map(w -> "Bob: \t" + w),
                jane.map(w -> "jane: \t" + w)).flatMap(innerObs -> Observable.just(innerObs).delay(random(3), TimeUnit.SECONDS));

        Observable.switchOnNext(quotes).subscribe(System.out::println);

        String[] letters = {"a", "b", "c"};

        Observable.fromArray(letters)
                .scan(new StringBuilder(), StringBuilder::append)
                .subscribe(x -> System.out.println(x));
        List<Integer> EVEN = new ArrayList<>();
        List<Integer> ODD = new ArrayList<>();
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 14);
        Observable.fromIterable(numbers)
                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD")
                .subscribe(group ->
                        group.subscribe((number) -> {
                            if (group.getKey().toString().equals("EVEN")) {
                                EVEN.add(number);
                            }else
                                ODD.add(number);
                        })
                );

        System.out.println(EVEN);
        System.out.println(ODD);


        String[] result = {""};
        ConnectableObservable<Long> connectable
                = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        connectable.subscribe(i -> result[0] += i);
        connectable.connect();
        Thread.sleep(1000000000l);
    }

    final static private Observable<String> speak(String quote, long millisPerChar) {
        String[] tokens = quote.replaceAll("[:,]", "").split(" ");
        Observable<String> words = Observable.fromArray(tokens);
        Observable<Long> absoluteDelay = words
                .map(String::length)
                .map(len -> len * millisPerChar)
                .scan((total, current) -> total + current);
        return words
                .zipWith(absoluteDelay.startWith(0L), Pair::of)
                .flatMap(pair -> Observable.just(pair.getFirst())
                        .delay(pair.getSecond(), TimeUnit.MILLISECONDS));
    }

    final static Long random(long max) {
        Random random = new Random();
        return (long) random.nextInt();
    }
}
