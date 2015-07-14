package com.appdynamics.demo;

import java.util.Map;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class SessionLoadTest extends StaticRequestLoadTest {


    public SessionLoadTest(String host, int port, int angularPort, int callDelay, String mysqlHost, String mysqlUserName,String mysqlPwd) {
        super(host, port, angularPort, callDelay, mysqlHost,mysqlUserName,mysqlPwd);
    }

    abstract void login();

    abstract void logout();

    abstract void performLoad();

    @Override
    protected void executeTest() {
        try {
            login();
            performLoad();
            logout();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            destroy();
        }
    }

    public String[] getUrls() {
        return null;
    }
}
