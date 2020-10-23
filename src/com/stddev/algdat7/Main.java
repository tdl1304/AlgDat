package com.stddev.algdat7;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "graphs/test.txt";
        String output = "graphs/compressedFile.txt";
        Huffman hm = new Huffman(path, output);
        hm.encode();
    }
}
