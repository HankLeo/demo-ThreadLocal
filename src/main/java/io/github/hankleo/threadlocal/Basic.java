package io.github.hankleo.threadlocal;

public class Basic {

    // ThreadLocal<T>
    public static ThreadLocal<Long> x = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            System.out.println("Initial value run.");
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        // get()方法驱动ThreadLocal的initialValue()方法，否则initialValue()不会被执行
        // 主线程的默认Id是1
        System.out.println("Main thread:" + x.get());

        // 尽管ThreadLocal被定义为static，但它是线程本地的变量
        // 每个线程都有自己的一套ThreadLocal
        new Thread() {
            @Override
            public void run() {
                System.out.println("New thread:" + x.get());
            }
        }.start();

        // set()时不会再initialValue
        x.set(108L);
        System.out.println("Main thread:" + x.get());

        // remove()之后再次get()会重新initialValue
        x.remove();
        System.out.println("Main thread:" + x.get());
    }

}
