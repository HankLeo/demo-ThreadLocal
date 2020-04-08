package io.github.hankleo.demo.threadlocal.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SynchronizedStatController {

    static Integer stat = 0;

    synchronized private void _add() throws InterruptedException {
        Thread.sleep(100);
        stat++;
    }

    @RequestMapping("/stat")
    public Integer stat() {
        return stat;
    }

    @RequestMapping("/add")
    public Integer add() throws InterruptedException {
        _add();
        return 1;
    }

}
