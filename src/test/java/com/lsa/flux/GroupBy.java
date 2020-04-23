package com.lsa.flux;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class GroupBy {
    public static void main(String[] args) {
        final List<Member> input = new ArrayList<Member>() {{
            add(new Member(1, "1"));
            add(new Member(2, "2"));
            add(new Member(3, "3"));
            add(new Member(4, "4"));
            add(new Member(5, "5"));
            add(new Member(1, "1"));
            add(new Member(2, "2"));
            add(new Member(3, "3"));
            add(new Member(4, "4"));
            add(new Member(5, "5"));
        }};

        Observable<Member> facts = Observable.fromIterable(input);
        Observable<GroupedObservable<Integer, Member>> grouped = facts.groupBy(Member::getId);
        grouped.subscribe(byId -> {
            byId.subscribe(member -> {
                System.out.println(Thread.currentThread().getName());
                update(member);

            });
        });

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Member {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "Member{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static void update(Member m) {
        System.out.println(m.toString());
    }
}
