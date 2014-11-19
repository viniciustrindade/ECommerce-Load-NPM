package com.appdynamics.demo;

import java.util.concurrent.*;
/**
 * Hello world!
 *
 */
public class LoadRunner
{
    private static int numOfUsers;
    private static int rampUpTime;
    private static int waitTime = 100;
    private static int timeBetweenRuns = 3 * 1000;
    private static int port;
    private static String host = "pm-demo.appdynamics.com";
    ScheduledExecutorService pool;

    public LoadRunner()  {

    }

    private void init() {
       pool = Executors.newScheduledThreadPool(numOfUsers);
    }

    private void run() {
        while (true) {
            for (int i = 0; i < numOfUsers; i++) {
               pool.schedule(new AcmeCartScenarioOneTest(host, port, waitTime), rampUpTime, TimeUnit.MILLISECONDS);
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

    public static void main( String[] args )
    {
       parseArgs(args);
       LoadRunner runner = new LoadRunner();
       runner.init();
       runner.run();
    }

    private static void parseArgs(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: numberOfUsers rampUpTime timeBetweenRuns baseUrl port [waitTime]");
        }
        numOfUsers = Integer.parseInt(args[0]);
        rampUpTime = Integer.parseInt(args[1]);
        timeBetweenRuns = Integer.parseInt(args[2]);
        host = args[3];
        port = Integer.parseInt(args[4]);

        if (args.length == 6) {
          waitTime = Integer.parseInt(args[5]);
        }

    }
}
