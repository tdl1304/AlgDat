package com.stddev.algdat7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "graphs/test.txt";
        Huffman hm = new Huffman(path);



        System.out.println(hm.toString());
    }


}
