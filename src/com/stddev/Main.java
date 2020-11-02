package com.stddev;

public class Main {

    public static void main(String[] args) {
        double[] data = {50,35,27,19,8,11,6,17,2,15,24,18,7,9,16,25,29,42,37,4,33};
        double[] data2 = rangeOf(1,55);

        double sampleVariance = sampleVariance(data2);
        double standardDevian = standardDevian(data2);
        //System.out.println(standardDevian);
    }

    public static double[] rangeOf(int from, int to) {
        if(from>to) return null;
        double[] range = new double[to-from +1 ];
        for (int i = 1; i <= to; i++) {
            range[i-1] = i;
        }
        return range;
    }

    public static double sampleVariance(double[] data) {
        double stdDev = standardDevian(data);
        return Math.pow(stdDev, 2);
    }

    public static double standardDevian(double[] data) {
        double sum = 0;
        double avg = avg(data);
        for (double f : data
        ) {
            sum+=Math.pow(f-(avg),2);
        }
        return Math.sqrt(sum/(data.length-1));
    }

    public static double avg(double[] data) {
        double sum = 0;
        for (double f : data
        ) {
            sum+=f;
        }
        return sum/data.length;
    }
}