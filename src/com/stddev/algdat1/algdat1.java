package com.stddev.algdat1;
import java.util.Date;

public class algdat1 {
    public static Date slutt;
    public static Date start;
    public static double time;

    public static void main(String[] args) {
        int rounds = 0;
        start = new Date();
        do {
            //velg mellom pow0(), pow1() eller Math.pow() for å testing
            pow1(0.02, 10000);
            slutt = new Date();
            ++rounds;
        } while(slutt.getTime()-start.getTime() < 1000);
        time = (double) (slutt.getTime()-start.getTime()) / (double) rounds;
        System.out.println("Rounds " + rounds + ", time " + time);

    }

    //2.1-1
    public static double pow0(double x, int n) {
        if(n == 0) return 1;
        return x*pow0(x,n-1);
    }
    //2.2-3
    public static double pow1(double x, int n) {
        if(n==0) {
            return 1;
        } else if(n % 2 == 0) {
            return pow1(x*x, n/2);
        } else {
            return x*pow1(x*x, (n-1)/2);
        }
    }

}
