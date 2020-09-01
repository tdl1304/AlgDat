package com.stddev;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/*Alternativ valgt: 1 Quicksort*/
public class algdat2 {
    static Random r;
    static Date start;
    static Date slutt;

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //hentet fra stackoverflow
    static void singlePivotQuickSort(int[] a, int p, int r) throws StackOverflowError
    {
        if(p<r)
        {
            int q=singlePartition(a,p,r);
            singlePivotQuickSort(a,p,q);
            singlePivotQuickSort(a,q+1,r);
        }
    }

    //hentet fra stackoverflow
    static int singlePartition(int[] a, int p, int r) {

        //finner midterste element som pivot
        int x = a[p + (r-p)/2];
        int i = p-1;
        int j = r+1;

        while (true) {
            while (++i < r && a[i] < x);
            while (--j > p && a[j] > x);

            if (i < j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            } else {
                return j;
            }
        }
    }

    //hentet fra geeks for geeks
    static void dualPivotQuickSort(int[] arr, int low, int high) throws StackOverflowError {
        if (low < high) {

            // piv[] stores left pivot and right pivot.
            // piv[0] means left pivot and
            // piv[1] means right pivot
            int[] piv;
            piv = dualPartition(arr, low, high);

            dualPivotQuickSort(arr, low, piv[0] - 1);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1);
            dualPivotQuickSort(arr, piv[1] + 1, high);
        }
    }

    static int[] dualPartition(int[] arr, int low, int high) {
        swapFix(arr, low, high);
        if (arr[low] > arr[high])
            swap(arr, low, high);

        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
                p = arr[low], q = arr[high];

        while (k <= g) {

            // If elements are less than the left pivot
            if (arr[k] < p) {
                swap(arr, k, j);
                j++;
            }

            // If elements are greater than or equal
            // to the right pivot
            else if (arr[k] >= q) {
                while (arr[g] > q && k < g)
                    g--;

                swap(arr, k, g);
                g--;

                if (arr[k] < p) {
                    swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // Bring pivots to their appropriate positions.
        swap(arr, low, j);
        swap(arr, high, g);

        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[]{j, g};
    }

    static int[] generateRandomArray(int size) {
        r = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt((int) Math.pow(2, 32));
        }
        return array;
    }

    static int[] generateArrayWithDuplicates(int size) {
        r = new Random();
        int[] arr = new int[size];
        int i = 0;
        int tempNum = 0;
        while (i < size) {
            if (i % 100 == 0) tempNum = r.nextInt((int) Math.pow(2, 32));
            arr[i] = tempNum;
            i++;
        }
        return arr;
    }

    static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    static void swapFix(int[] arr, int low, int high) {
        swap(arr, low, low + (high - low) / 3); //swap arr[low] with arr[low+(high-low)/3]
        swap(arr, high, high - (high - low) / 3); //swap arr[high] with arr[high-(high-low)/3]
    }

    //summer test og rekke test
    static boolean tests(int[] sortedArr, int[] unsortedArr) {
        int unsortedSum = 0, sortedSum = 0;
        boolean sorted = true;

        for (int num : unsortedArr
        ) {
            unsortedSum += num;
        }
        for (int num : sortedArr
        ) {
            sortedSum += num;
        }

        for (int i = 0; i < sortedArr.length - 2; i++) {
            if (!(sortedArr[i + 1] >= sortedArr[i])) sorted = false;
        }

        if (sortedSum == unsortedSum && sorted) return true;
        else return false;
    }

    // Driver code
    public static void main(String[] args) {
        //initialization
        int size = 100000000; //change sample size
        double time = 0;
        int low = 0;
        int high = size - 1;
        int[] dupe = generateArrayWithDuplicates(size);
        int[] dupe1 = Arrays.copyOf(dupe, size);
        int[] dupeCopy = Arrays.copyOf(dupe, size);
        int[] randoms = generateRandomArray(size);
        int[] randoms1 = Arrays.copyOf(randoms, size);
        int[] randomsCopy = Arrays.copyOf(randoms, size);
        int[] sortedArray = generateSortedArray(size);
        int[] sorted1 = Arrays.copyOf(sortedArray, size);
        int[] sortedCopy = Arrays.copyOf(sortedArray, size);


        System.out.println("Duplicates single pivot:");
        try {
            start = new Date();
            singlePivotQuickSort(dupe, low, high);
            slutt = new Date();
            time = slutt.getTime() - start.getTime();
            if (tests(dupe, dupeCopy)) System.out.println("Tests: ok, time: " + time);
            else System.out.println("Tests: failed");
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow Error");
        }


        System.out.println("Duplicates dual pivot:");
        try {
            start = new Date();
            dualPivotQuickSort(dupe1, low, high);
            slutt = new Date();
            time = slutt.getTime() - start.getTime();
            if (tests(dupe1, dupeCopy)) System.out.println("Tests: ok, time: " + time);
            else System.out.println("Tests: failed");
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow Error");
        }


        System.out.println("Random numbers single pivot:");
        try {
            start = new Date();
            singlePivotQuickSort(randoms, low, high);
            slutt = new Date();
            time = slutt.getTime() - start.getTime();
            if (tests(randoms, randomsCopy)) System.out.println("Tests: ok, time: " + time);
            else System.out.println("Tests: failed");
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow Error");
        }


        System.out.println("Random numbers dual pivot:");
        try {
            start = new Date();
            dualPivotQuickSort(randoms1, low, high);
            slutt = new Date();
            time = slutt.getTime() - start.getTime();
            if (tests(randoms1, randomsCopy)) System.out.println("Tests: ok, time: " + time);
            else System.out.println("Tests: failed");
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow Error");
        }


        System.out.println("Sorted array single pivot:");
        try {
            start = new Date();
            singlePivotQuickSort(sortedArray, low, high);
            slutt = new Date();
            time = slutt.getTime() - start.getTime();
            if (tests(sortedArray, sortedCopy)) System.out.println("Tests: ok, time: " + time);
            else System.out.println("Tests: failed");
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow Error");
        }


        System.out.println("Sorted array dual pivot:");
        try {
            start = new Date();
            dualPivotQuickSort(sorted1, low, high);
            slutt = new Date();
            time = slutt.getTime() - start.getTime();
            if (tests(sorted1, sortedCopy)) System.out.println("Tests: ok, time: " + time);
            else System.out.println("Tests: failed");
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow Error");
        }


    }
}


