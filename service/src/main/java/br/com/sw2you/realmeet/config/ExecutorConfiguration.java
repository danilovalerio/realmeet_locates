package br.com.sw2you.realmeet.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorConfiguration {

    @Bean
    public Executor constrollerExecutor(
            @Value("${realmeet.taskExecutor.pool.coreSize:20}") int corePoolSize,
            @Value("${realmeet.taskExecutor.pool.maxSize:20}") int maxPoolSize,
            @Value("${realmeet.taskExecutor.pool.queueCapacity:50}") int queueCapacity,
            @Value("${realmeet.taskExecutor.pool.keepAliveSeconds:60}") int keepAliveSeconds) {
        /**
         * Retorna o pool de threads
         */
        return new ThreadPoolExecutor(
                corePoolSize, //tamanho base do Pool
                maxPoolSize, //max de threads no Pool
                keepAliveSeconds, //timeout para thread
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity, true) //fila baseada em array e é bloqueante, fair true distribuicao no uso da fila
        );
    }
}