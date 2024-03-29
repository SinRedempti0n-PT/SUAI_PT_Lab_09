package com.sinredemption;

public class Deadlock {
    private static Object Lock1 = new Object();
    private static Object Lock2 = new Object();

    public static class Thread1 extends Thread {
        
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");

                try {Thread.sleep(10);} catch (InterruptedException e) {}
                
                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }

    public static class Thread2 extends Thread {
        
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding lock 2...");

                try {Thread.sleep(10);} catch (InterruptedException e) {}

                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (Lock1) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }

}