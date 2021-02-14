package com.sinredemption;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main{

    public static void main(String args[]) {
        /*Deadlock.Thread1 T1 = new Deadlock.Thread1();
        Deadlock.Thread2 T2 = new Deadlock.Thread2();
        T1.start();
        T2.start();*/
        try {
            HashMap<String, Integer> map = new RWriter(args).getMap();
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach(System.out::println);
        } catch (Exception e){
            e.getCause();
        }
     }
}