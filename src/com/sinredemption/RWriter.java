package com.sinredemption;

import java.nio.file.*;
import java.util.HashMap;

public class RWriter {

    private HashMap<String, Integer> mapGlobal;

    private class scan extends Thread{

        private String[] words;
        private HashMap<String, Integer> map;

        public scan(String filename) throws Exception{
            this.map = new HashMap<>();
            this.words = new String(Files.readAllBytes(Paths.get(filename)))
                        .replace('.', ' ')
                        .replace(',', ' ')
                        .replace('\n', ' ')
                        .replace('\r', ' ')
                        .split(" ");
        }

        @Override
        public void run() {
            for(String w: words)
                if(!w.equals(""))
                    if(map.containsKey(w))
                        map.put(w, map.get(w) + 1);
                    else
                        map.put(w, 1);
            synchronized (mapGlobal){
                map.forEach(
                        (key, value)
                                -> mapGlobal.merge(
                                key,
                                value,
                                (v1, v2)
                                        -> v1 == v2
                                        ? v1
                                        : v1++));
            }
        }
    }

    public RWriter(String[] files) throws Exception{
        this.mapGlobal = new HashMap<>();
        for(String file: files)
            new scan(file).run();
    }

    public HashMap<String, Integer> getMap(){
        return mapGlobal;
    }
}
