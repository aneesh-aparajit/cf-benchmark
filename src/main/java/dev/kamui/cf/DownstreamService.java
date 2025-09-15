package dev.kamui.cf;

import dev.kamui.CfLogger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownstreamService {

    private static final CfLogger logger = new CfLogger(DownstreamService.class.getName());
    private static final ExecutorService executor = Executors.newFixedThreadPool(100, r -> {
        Thread t = new Thread(r);
        t.setName("downstream-service-" + System.currentTimeMillis());
        return t;
    });

    public CompletableFuture<Integer> mockApiCall(String traceId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                logger.info("in mockApiCal", traceId);
                Thread.sleep(500);
                return 1;
            } catch (Exception ex) {
                logger.error("error in mockApiCall", traceId);
            }
            return null;
        }, executor);
    }

}
