package io.github.hankleo.demo.threadlocal.hello.model;

public class Val<T> {
    T value;

    public Val(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
