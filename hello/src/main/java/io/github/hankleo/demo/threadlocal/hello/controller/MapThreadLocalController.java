package io.github.hankleo.demo.threadlocal.hello.controller;

import io.github.hankleo.demo.threadlocal.hello.model.Val;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/map-thread-local")
public class MapThreadLocalController {

    //static Set<Val<Integer>> set = new HashSet<>();
    static CopyOnWriteArraySet<Val<Integer>> set = new CopyOnWriteArraySet<>();

    static ThreadLocal<Val<Integer>> stat = new ThreadLocal() {
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> val = new Val(0);
            set.add(val);
            return val;
        }
    };

    private void _add() throws InterruptedException {
        Thread.sleep(100);
        Val<Integer> val = stat.get();
        val.setValue(val.getValue() + 1);
    }

    @RequestMapping("/stat")
    public Integer stat() {
        //AtomicReference<Integer> sum = new AtomicReference<>(0);
        //set.forEach((val) -> sum.updateAndGet(v -> v + val.getValue()));
        return set.stream().map(Val::getValue).reduce(Integer::sum).get();
        //return sum.get();
    }

    @RequestMapping("/add")
    public Integer add() throws InterruptedException {
        _add();
        return 1;
    }

}
