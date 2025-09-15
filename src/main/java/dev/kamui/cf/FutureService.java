package dev.kamui.cf;

import dev.kamui.CfLogger;
import dev.kamui.StringUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureService {

    private static final CfLogger logger = new CfLogger(FutureService.class.getName());
    private static final DownstreamService downstreamService = new DownstreamService();
    private static final ExecutorService executor = Executors.newFixedThreadPool(100, r -> {
        Thread t = new Thread(r);
        t.setName("future-service-" + System.currentTimeMillis());
        return t;
    });

    public CompletableFuture<Integer> chainingFunctionsWithoutSwappingExecutor() {
        String traceId = StringUtil.generateTraceId();
        return CompletableFuture.completedFuture(null)
                .thenApply(__ -> traceId)
                .thenCompose(downstreamService::mockApiCall)
                .thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                });
    }

    public CompletableFuture<Integer> chainingFunctionsWithSwappingToCommonPool() {
        String traceId = StringUtil.generateTraceId();
        return CompletableFuture.completedFuture(null)
                .thenApply(__ -> traceId)
                .thenCompose(downstreamService::mockApiCall)
                .thenApplyAsync(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                });
    }

    public CompletableFuture<Integer> chainingFunctionsWithSwappingToCustomPool() {
        String traceId = StringUtil.generateTraceId();
        return CompletableFuture.completedFuture(null)
                .thenApply(__ -> traceId)
                .thenCompose(downstreamService::mockApiCall)
                .thenApplyAsync(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }, executor).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                }).thenApply(value -> {
                    logger.info("value: " + value, traceId);
                    value *= 2;
                    return value;
                });
    }

}
