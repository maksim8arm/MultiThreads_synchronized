package com.company;

import java.util.*;

public class Main {
    private static String text;

    public static void main(String[] args) throws InterruptedException {

        Runnable logic = () -> {
            lineOfLetter();
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(logic));
            threads.get(i).start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        Set<Map.Entry<Integer, Integer>> entries = sizeToFreq.entrySet();
        Integer keyBig = 0;
        for (Map.Entry<Integer, Integer> pair : entries) {
            Integer key = pair.getKey();
            if (key > keyBig) {
                keyBig = key;
            }
        }
        System.out.println("Самое частое количество повторений " + keyBig
                + " (встретилось " + sizeToFreq.get(keyBig) + " раз)");
        System.out.println("Другие размеры:");
        for (Map.Entry<Integer, Integer> pair : entries) {
            Integer key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println("- " + key + " (" + value + " раз) " );
        }
    }

    public static synchronized void lineOfLetter() {
        text = generateRoute("RLRFR", 100);
        int maxSize = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'R') maxSize++;
        }
        if (!sizeToFreq.containsKey(maxSize)) {
            sizeToFreq.put(maxSize, 1);
        } else {
            int newNum = sizeToFreq.get(maxSize) + 1;
            sizeToFreq.put(maxSize, newNum);
        }
    }

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}

