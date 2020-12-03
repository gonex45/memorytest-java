package org.gorendo.memorytest;

import java.sql.SQLOutput;
import java.util.Vector;

public class Main {

    private static float CAP = 1f;
    private static int ONE_MB = 1024 * 1024;

    private static Vector cache = new Vector();

    public static void main(String[] args) throws InterruptedException {
        Runtime rt = Runtime.getRuntime();

        long maxMemBytes = rt.maxMemory();
        long usedMemBytes = rt.totalMemory() - rt.freeMemory();
        long freeMemBytes = rt.maxMemory() - usedMemBytes;

        long allocBytes = (long)Math.floor(freeMemBytes * CAP);

        System.out.println(allocBytes);

        System.out.println("Initial free memory:" + freeMemBytes / ONE_MB + "MB");
        System.out.println("Max memory:" + maxMemBytes / ONE_MB + "MB");
        System.out.println("Used memory:" + usedMemBytes / ONE_MB + "MB");

        System.out.println("Reserve:" + allocBytes / ONE_MB + "MB");

//        for (int i = 0; i < (allocBytes / 1) / ONE_MB; i++) {
//            System.out.println("ADD" + i);

        new Thread(new Runnable() {
            @Override
            public void run() {
                long usedMemBytes = rt.totalMemory() - rt.freeMemory();
                long freeMemBytes = rt.maxMemory() - usedMemBytes;
                try{
                    System.out.println(allocBytes / ONE_MB / 10);
//                    for (int i = 0; i < allocBytes / ONE_MB / 10; i++) {
                    while (usedMemBytes <= (allocBytes - ONE_MB)) {
//            System.out.println(freeMemBytes);
                        cache.add(new byte[ONE_MB * 10]);
                        usedMemBytes = rt.totalMemory() - rt.freeMemory();
                        freeMemBytes = rt.maxMemory() - usedMemBytes;

                        System.out.println("Used memory:" + usedMemBytes / ONE_MB + "MB");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw e;
                }

                System.out.println("Used memory:" + usedMemBytes / ONE_MB + "MB");
                System.out.println("Free memory:" + freeMemBytes / ONE_MB + "MB");
            }
        }).start();


        usedMemBytes = rt.totalMemory() - rt.freeMemory();
        freeMemBytes = rt.maxMemory() - usedMemBytes;

        System.out.println("Used memory:" + usedMemBytes / ONE_MB + "MB");
        System.out.println("Free memory:" + freeMemBytes / ONE_MB + "MB");
        Thread.sleep(90000L);
    }
}
