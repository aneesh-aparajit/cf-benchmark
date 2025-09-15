package dev.kamui;

import dev.kamui.cf.FutureService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

class MainTest {

    private final FutureService fs = new FutureService();

    @Test
    public void chainFunctionsWithoutSwappingExecutor() {
        for (int i = 0; i < 60; i++) {
            List<CompletableFuture<CompletableFuture<Integer>>> completableFutures = new ArrayList<>();
            for (int j = 0; j < 500; j++) {
                completableFutures.add(CompletableFuture.supplyAsync(fs::chainingFunctionsWithoutSwappingExecutor));
            }
            for (CompletableFuture<CompletableFuture<Integer>> future : completableFutures) {
                future.join().join();
            }
        }
    }

    @Test
    public void chainFunctionsWithSwappingToCommonPool() {
        for (int i = 0; i < 60; i++) {
            List<CompletableFuture<CompletableFuture<Integer>>> completableFutures = new ArrayList<>();
            for (int j = 0; j < 500; j++) {
                completableFutures.add(CompletableFuture.supplyAsync(fs::chainingFunctionsWithSwappingToCommonPool));
            }
            for (CompletableFuture<CompletableFuture<Integer>> future : completableFutures) {
                future.join().join();
            }
        }
    }

    @Test
    public void chainFunctionsWithSwappingToCustomPool() {
        for (int i = 0; i < 60; i++) {
            List<CompletableFuture<CompletableFuture<Integer>>> completableFutures = new ArrayList<>();
            for (int j = 0; j < 500; j++) {
                completableFutures.add(CompletableFuture.supplyAsync(fs::chainingFunctionsWithSwappingToCustomPool));
            }
            for (CompletableFuture<CompletableFuture<Integer>> future : completableFutures) {
                future.join().join();
            }
        }
    }

}