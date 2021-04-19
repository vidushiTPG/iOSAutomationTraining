package com.tpg.appium.learning.utility;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;

public class AppiumServerUtility {

    public static void start() {
        getInstance().start();
        /*if (checkIfAppiumServerIsRunnning(4723)) {
            getInstance().start();
            System.out.println("Server started from here");
        } else {
            System.out.println("Server already running");
        }*/

    }
    public static AppiumDriverLocalService getInstance() {
        AppiumDriverLocalService appiumDriverLocalService;
        HashMap<String, String> environment = new HashMap<String, String>();
        //Path to Carthage
        environment.put("PATH", "/usr/local/Cellar/carthage/0.36.0/bin" + System.getenv("PATH"));
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder
                //Path to Appium server
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                //Path to Node
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .usingPort(4723)
                //.withEnvironment(environment)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File("ServerLogs/server.log"));
        appiumDriverLocalService=AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumDriverLocalService.clearOutPutStreams(); //stop printing appium logs to console
        return appiumDriverLocalService;
    }

    public static void stop() {
        Runtime runtime = Runtime.getRuntime();
        String[] command = {"/usr/bin/killall", "-9", "node"};
        try {
            runtime.exec(command);
            System.out.println("Server Stopped !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(false);
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port), 1);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

}
