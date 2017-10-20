package com.example;

public class MyClass {

    public static void main(String... args) {

        Thread thread;
        thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println("i = "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        });thread.start();
    }
}
