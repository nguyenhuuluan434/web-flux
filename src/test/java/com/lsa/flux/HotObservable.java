package com.lsa.flux;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.stream.IntStream;

public class HotObservable {

    public static class ComputeFunction {
        public static void compute(Integer v) {
            try {
                System.out.println("compute integer v: " + v);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PublishSubject<Integer> source = PublishSubject.<Integer>create();

        source.observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        IntStream.range(1, 1_000_000_000).forEach(source::onNext);
        Thread.sleep(1000000000);
    }
}
