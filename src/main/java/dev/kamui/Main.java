package dev.kamui;

import dev.kamui.cf.FutureService;

public class Main {
    public static void main(String[] args) {
        FutureService fs = new FutureService();
        System.out.println("Starting, thread: " + Thread.currentThread().getName());
        Integer value = fs.chainingFunctionsWithoutSwappingExecutor().join();
        System.out.println("Value: " + value + "; Thread: " + Thread.currentThread().getName());
    }
}