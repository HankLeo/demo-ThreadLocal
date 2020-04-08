package io.github.hankleo.demo.threadlocal.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsafe")
public class UnsafeStatController {

    static Integer stat = 0;

    @RequestMapping("/stat")
    public Integer stat() {
        return stat;
    }

    @RequestMapping("/add")
    public Integer add() {
        stat++;
        return 1;
    }
}
