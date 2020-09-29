package com.stddev.algdat3;

import java.util.Iterator;
import java.util.LinkedList;

public class algdat3a {
    //Driver method
    public static void main(String[] args) {
        LinkedList<Integer> num1 = createNumberList(args[0]);
        LinkedList<Integer> num2 = createNumberList(args[2]);
        LinkedList<Integer> sum;
        if(args[1].equalsIgnoreCase("+")) {
            sum = add(num1,num2);
            String s = toString(sum);
            System.out.println(s);
        } else if(args[1].equalsIgnoreCase("-")){
            sum = subtract(num1, num2);
            String s = toString(sum);
            System.out.println(s);
        } else{
            System.out.println("bad input");
        }


    }

    public static LinkedList<Integer> createNumberList(String num) {
        String[] nums = num.split("");
        LinkedList<Integer> numList = new LinkedList<>();
        for (String s : nums) {
            numList.add(Integer.parseInt(s));
        }
        numList.addFirst(0);
        return numList;
    }

    // num1 - num2
    public static LinkedList<Integer> subtract(LinkedList<Integer> num1, LinkedList<Integer> num2) {
        LinkedList<Integer> diff = new LinkedList<>();
        LinkedList<Integer> bigList;
        LinkedList<Integer> smallList;
        //removes 0 at index 0
        int sign = 0;
        int sum = 0;
        num1.removeFirst();
        num2.removeFirst();

        //Determine list size and value difference
        switch (compareLists(num1, num2)) {
            case -1:
                bigList = num2;
                smallList = num1;
                sign = -1;
                break;
            case 0:
                LinkedList<Integer> zero = new LinkedList<>();
                zero.add(0);
                zero.add(0);
                return zero;
            case 1:
                bigList = num1;
                smallList = num2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + compareLists(num1, num2));
        }


        Iterator iterator = bigList.descendingIterator();
        while (iterator.hasNext()) {
            if (smallList.size() != 0) {
                sum += (int) iterator.next() - smallList.removeLast();
                if (sum < 0) {
                    sum += 10;
                    diff.addFirst(sum);
                    sum = -1; // add a 1 to the next part-sum
                } else {
                    diff.addFirst(sum);
                    sum = 0;
                }
            } else {
                diff.addFirst((int) iterator.next());
            }
        }
        diff.addFirst(sign);
        return diff;
    }


    public static int compareLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        if (list1.size() > list2.size()) {
            return 1;
        } else if (list1.size() < list2.size()) {
            return -1;
        } else {
            boolean equals = true;
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) > list2.get(i)) {
                    return 1;
                }
            }
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    equals = false;
                    break;
                }

            }
            if (equals) return 0;
            else return -1;
        }
    }


    public static LinkedList<Integer> add(LinkedList<Integer> num1, LinkedList<Integer> num2) {
        LinkedList<Integer> sumList = new LinkedList<>();
        //num1 is bigger than num2
        LinkedList<Integer> bigList;
        LinkedList<Integer> smallList;

        //Determine list size difference
        //and assign values;
        if (num1.size() > num2.size()) {
            bigList = num1;
            smallList = num2;
        } else {
            bigList = num2;
            smallList = num1;
        }

        Iterator iterator = bigList.descendingIterator();
        int sum = 0;
        while (iterator.hasNext()) {
            if (smallList.size() != 0) {
                sum += (int) iterator.next() + smallList.removeLast();
                if (sum > 9) {
                    sum -= 10;
                    sumList.addFirst(sum);
                    sum = 1; // add a 1 to the next part-sum
                } else {
                    sumList.addFirst(sum);
                    sum = 0;
                }
            } else {
                sum += (int) iterator.next();
                if(sum > 9) {
                    sum -= 10;
                    sumList.addFirst(sum);
                    sum = 1; // add a 1 to the next part-sum
                } else {
                    sumList.addFirst(sum);
                    sum = 0;
                }

            }
        } //while
        return sumList;

    }

    public static String toString(LinkedList<Integer> num) {
        String number = "";
        if(num.removeFirst() == -1) number += "-";
        for (int i = 0; i < num.size(); i++) {
            number += num.get(i);
        }
        return number;
    }
}




