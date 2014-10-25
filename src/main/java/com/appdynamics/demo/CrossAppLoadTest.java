package com.appdynamics.demo;

/**
 * Created by aleftik on 10/25/14.
 */
public class CrossAppLoadTest extends StaticRequestLoadTest {
    private static final String [] URLS = {"/appdynamicspilot/crossapp"};

    public CrossAppLoadTest(String host,int port, int callDelay) {
        super(host,port, callDelay);
    }


    @Override
    String[] getUrls() {
        return URLS;
    }
}
