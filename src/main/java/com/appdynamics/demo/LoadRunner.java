package com.appdynamics.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class LoadRunner {
    private static int numOfUsers;
    private static int rampUpTime;
    private static int waitTime = 100;
    private static int timeBetweenRuns = 3 * 1000;
    private static int port;
    private static int angularPort;
    private static String host = "pm-demo.appdynamics.com";
    private static String mysqlHost = "";
    private static String mysqlUserName = "";
    private static String mysqlPwd = "";


    ScheduledExecutorService pool;

    public LoadRunner() {

    }

    private void init() {
        pool = Executors.newScheduledThreadPool(numOfUsers);
    }

    private void run() {
        while (true) {
            for (int i = 0; i < numOfUsers; i++) {
                pool.schedule(new ECommerceCheckout(host, port, angularPort, waitTime, mysqlHost,mysqlUserName,mysqlPwd), rampUpTime, TimeUnit.MILLISECONDS);
                pool.schedule(new ECommerceAngularCheckout(host, port, angularPort, waitTime, mysqlHost,mysqlUserName,mysqlPwd), rampUpTime, TimeUnit.MILLISECONDS);
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
            System.out.println("Usage: numberOfUsers rampUpTime timeBetweenRuns baseUrl port angularPort mysqlHost mysqlUserName mysqlPwd [waitTime]");
        }
        numOfUsers = Integer.parseInt(args[0]);
        rampUpTime = Integer.parseInt(args[1]);
        timeBetweenRuns = Integer.parseInt(args[2]);
        host = args[3];
        port = Integer.parseInt(args[4]);
        angularPort = Integer.parseInt(args[5]);
        mysqlHost = String.valueOf(args[6]);
        mysqlUserName = String.valueOf(args[7]);
        mysqlPwd = String.valueOf(args[8]);

        if (args.length == 10) {
            waitTime = Integer.parseInt(args[9]);
        }

    }
}
