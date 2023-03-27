package com.example.show_problem;

import com.example.show_problem.service.DbService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.function.FailableRunnable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class OptimisticAndPessimisticLockingApplication implements CommandLineRunner {

    @Resource
    private DbService dbService;

    public static void main(String[] args) {
        SpringApplication.run(OptimisticAndPessimisticLockingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        dbService.loadData();

        ExecutorService executor = Executors.newFixedThreadPool(2);


        executor.execute(safeRunnable(dbService::changeFlight1));
        executor.execute(safeRunnable(dbService::changeFlight2));
        dbService.findAllFlight();
        executor.shutdown();
    }

    private Runnable safeRunnable(FailableRunnable<Exception> runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
