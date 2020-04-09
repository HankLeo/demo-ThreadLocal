package io.github.hankleo.demo.threadlocal.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread-local")
public class ThreadLocalController {

    static ThreadLocal<Integer> stat = ThreadLocal.withInitial(() -> 0);

    private void _add() throws InterruptedException {
        Thread.sleep(100);
        stat.set(stat.get() + 1);
    }

    @RequestMapping("/stat")
    public Integer stat() {
        // Spring本身有自己的线程池，每个线程会有各自的ThreadLocal变量stat，所以结果会是多个，多个结果相加才是真实计算结果
        return stat.get();
    }

    @RequestMapping("/add")
    public Integer add() throws InterruptedException {
        _add();
        return 1;
    }

}
