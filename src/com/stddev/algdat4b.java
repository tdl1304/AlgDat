
package com.stddev;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class algdat4b {
    public static Date slutt;
    public static Date start;
    public static double time;

    public static void main(String[] args) {
        IntegerHashTable hashTable;
        int size = 11000000;
        int[] randomNums = getArrayWithRandomNumbers(size - 1000000, size * 100);

        //custom hashmap
        System.out.println("Custom hashmap test:");
        int rounds = 0;
        start = new Date();
        do {
            hashTable = new IntegerHashTable(size);
            for (int n : randomNums
            ) {
                hashTable.put(String.valueOf(n), n);
            }
            slutt = new Date();
            ++rounds;
        } while (slutt.getTime() - start.getTime() < 20000);
        time = (double) (slutt.getTime() - start.getTime()) / (double) rounds;
        System.out.println("Rounds " + rounds + ", avg time " + time);
        System.out.println("Collisions: " + hashTable.getCollision() + " Lastfaktor: " + hashTable.getLastFactor() + " Didn't manage to insert: " + hashTable.getErrors()+"\n");

        //Java hashmap
        System.out.println("Java hashmaptest:");
        HashMap<String, Integer> hashmap;
        rounds = 0;
        start = new Date();
        do {
            hashmap = new HashMap<>();
            for (int n : randomNums
            ) {
                hashmap.put(String.valueOf(n), n);
            }
            slutt = new Date();
            ++rounds;
        } while (slutt.getTime() - start.getTime() < 20000);
        time = (double) (slutt.getTime() - start.getTime()) / (double) rounds;
        System.out.println("Rounds " + rounds + ", avg time " + time);

    }

    static int[] getArrayWithRandomNumbers(int size, int upperBound) {
        int[] temp = new int[size];
        Random r = new Random();
        for (int i = 0; i < temp.length; i++) {
            temp[i] = r.nextInt(upperBound);
        }
        return temp;
    }
}

class IntegerHashTable {
    private Integer[] buckets;
    private final int size;
    //teller kollisjoner ved innsetting
    private int collision;
    //antall innlagte elementer i buckets[]
    private int elements;
    //dersom noe ikke ble inserta
    private int errors;

    //lastfactor is elements divided by total
    public IntegerHashTable(int size) {
        this.size = size;
        buckets = new Integer[size];
        collision = 0;
        elements = 0;
    }

    public double getLastFactor() {
        double m = size;
        double n = elements;
        return n / m;
    }

    public int getErrors() {
        return errors;
    }

    public int getSize() {
        return size;
    }

    public int getCollision() {
        return collision;
    }

    private int hashCode(String key) {
        int hash = Integer.parseInt(key);
        return hash % size;
    }

    private int doubleHashCode(String key) {
        int x = Integer.parseInt(key);
        return 9-x%9;
    }

    public void put(String key, int value) {
        int hash = hashCode(key);
        if (buckets[hash] == null) {
            elements++;
            buckets[hash] = value;
        } else {
            collision++;
            int hash2 = doubleHashCode(key);
            int i = 0;
            while (i < size) {
                hash = (hash + hash2) % (size);
                if (buckets[hash] == null) {
                    elements++;
                    buckets[hash] = value;
                    return;
                } else i++;
            }
            errors++;
            //System.out.println("Cannot put key:" + key + " and value:" + value + " anywhere");
        }
    }
}
