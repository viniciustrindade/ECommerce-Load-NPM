package com.appdynamics.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class LoadRunner {
    private static int numOfUsers;
    private static int timeBetweenRuns = 3 * 1000;
    private static int port;
    private static String host = "";

    ScheduledExecutorService pool;

    public LoadRunner() {

    }

    private void init() {
        pool = Executors.newScheduledThreadPool(numOfUsers);
    }

    private void run() {
        while (true) {
            for (int i = 0; i < numOfUsers; i++) {
                ECommerceRest ecommerceRest = new ECommerceRest();
                ecommerceRest.generateLoad(host,port);
            }
            sleep();
        }

    }

    private void sleep() {
        try {
            Thread.currentThread().sleep(timeBetweenRuns);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        parseArgs(args);
        LoadRunner runner = new LoadRunner();
        runner.init();
        runner.run();
    }


    private static void parseArgs(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: numberOfUsers timeBetweenRuns baseUrl port");
        }
        numOfUsers = Integer.parseInt(args[0]);
        timeBetweenRuns = Integer.parseInt(args[1]);
        host = args[2];
        port = Integer.parseInt(args[3]);

    }
}